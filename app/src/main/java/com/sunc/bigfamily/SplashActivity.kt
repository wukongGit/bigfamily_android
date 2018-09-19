package com.sunc.bigfamily

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sunc.app.SealAppContext
import com.sunc.base.BaseBindingActivity
import com.sunc.bigfamily.databinding.ActivitySplashBinding
import com.sunc.db.SharedPreferencesHelper
import com.sunc.utils.NetUtils
import com.sunc.utils.StatusBarUtils.setTranslucentStatus
import io.rong.imkit.RongIM
import kotlinx.android.synthetic.main.activity_splash.*
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

class SplashActivity : BaseBindingActivity<ActivitySplashBinding>() {
    val TAG = "SplashActivity"
    private val picUrl = "http://api.dujin.org/bing/1920.php"
    private var token : String? = null
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
        connect()
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
        if (!TextUtils.isEmpty(token)) {
            goToMain()
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

    private fun connect() {
        token = "oia7zLjQEIpMX3IBdVwW5Ftkutf0UcSFzGVFXG656EpTLGvlpO0h+CXgCwuLp4BHOGHMM/4XZQG7oVM/PE23QQ==";//SharedPreferencesHelper.getInstance().getData(SharedPreferencesHelper.CONFIG_TOKEN, "").toString()
        if (!TextUtils.isEmpty(token)) {
            if (NetUtils.isConnected(this)) {
                RongIM.connect(token, SealAppContext.getInstance().connectCallback)
            }
        }
    }

    override fun onDestroy() {
        if(compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe()
        }
        super.onDestroy()
    }

}
