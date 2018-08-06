package com.sunc.bigfamily

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import io.rong.imkit.RongIM
import io.rong.imlib.RongIMClient



class SplashActivity : AppCompatActivity() {
    private var context: Context? = null
    private val handler = android.os.Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        context = this
        //val sp = getSharedPreferences("config", Context.MODE_PRIVATE)
        val cacheToken = "hE/OJW+y2Cq8sXxU6fORvFtkutf0UcSFzGVFXG656Ep/Bg+b27kQHWWKD/s4ncqIf6ykIUK/bvt7ry8jo9Dfd7de/+YaWLrs"//sp.getString("loginToken", "")
        if (!TextUtils.isEmpty(cacheToken)) {
            connect(cacheToken)
            handler.postDelayed({ goToMain() }, 800)
        } else {
            handler.postDelayed({ goToLogin() }, 800)
        }
    }

    private fun connect(token: String) {
        RongIM.connect(token, object : RongIMClient.ConnectCallback() {
            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            override fun onTokenIncorrect() {
                Log.d("connect", "onTokenIncorrect:")
            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            override fun onSuccess(userid: String) {
                Log.d("connect", "onSuccess:" + userid)
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            override fun onError(errorCode: RongIMClient.ErrorCode) {
                Log.d("connect", "onError:" + errorCode)
            }
        })
    }

    private fun goToMain() {
        startActivity(Intent(context, MainActivity::class.java))
        finish()
    }

    private fun goToLogin() {
        startActivity(Intent(context, LoginActivity::class.java))
        finish()
    }
}
