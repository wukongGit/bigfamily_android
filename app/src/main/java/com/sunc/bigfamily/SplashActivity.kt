package com.sunc.bigfamily

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sunc.base.BaseBindingActivity
import com.sunc.bigfamily.databinding.ActivitySplashBinding
import com.sunc.utils.StatusBarUtils.setTranslucentStatus
import io.rong.imkit.RongIM
import io.rong.imlib.RongIMClient
import kotlinx.android.synthetic.main.activity_splash.*
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

class SplashActivity : BaseBindingActivity<ActivitySplashBinding>() {
    val TAG = "SplashActivity"
    private val picUrl = "http://api.dujin.org/bing/1920.php"
    private var compositeSubscription = CompositeSubscription()

    override fun createDataBinding(savedInstanceState: Bundle?): ActivitySplashBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    override fun initView(savedInstanceState: Bundle?) {
        setTranslucentStatus(this, true)
        Glide.with(this).load(picUrl).centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_ad)
        startUp()
    }

    private fun startUp() {
        compositeSubscription.add(countDown(3)
                .doOnSubscribe({ tv_skip.text = "跳过 4" })
                .subscribe(object : Observer<Int> {
                    override fun onError(e: Throwable?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onNext(t: Int?) {
                        tv_skip.text = "跳过${t?.plus(1)} "
                    }

                    override fun onCompleted() {
                        toMain()
                    }
                }))
        fl_ad.setOnClickListener {
            toMain()
        }
    }

    /**
     * 倒计时
     * @param time 秒
     */
    private fun countDown(time: Int): Observable<Int> {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map { t -> time - t.toInt() }
                .take((time + 1))
    }

    /**
     * 跳转到 MainActivity 并取消订阅
     */
    private fun toMain() {
        if(compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe()
        }
        val sp = getSharedPreferences("config", Context.MODE_PRIVATE)
        //val cacheToken = "hE/OJW+y2Cq8sXxU6fORvFtkutf0UcSFzGVFXG656Ep/Bg+b27kQHWWKD/s4ncqIf6ykIUK/bvt7ry8jo9Dfd7de/+YaWLrs"
        val cacheToken = sp.getString("loginToken", "")
        if (!TextUtils.isEmpty(cacheToken)) {
            connect(cacheToken)
        } else {
            goToLogin()
        }

    }

    private fun goToMain() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    private fun goToLogin() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finish()
    }

    private fun connect(token: String) {
        RongIM.connect(token, object : RongIMClient.ConnectCallback() {
            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            override fun onTokenIncorrect() {
                Log.d(TAG, "onTokenIncorrect:")
            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            override fun onSuccess(userid: String) {
                Log.d(TAG, "onSuccess:$userid")
                goToMain()
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            override fun onError(errorCode: RongIMClient.ErrorCode) {
                Log.d(TAG, "onError:$errorCode")
            }
        })
    }

    override fun onDestroy() {
        if(compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe()
        }
        super.onDestroy()
    }

}
