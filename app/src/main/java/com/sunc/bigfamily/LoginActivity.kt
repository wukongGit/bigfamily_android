package com.sunc.bigfamily

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Selection
import android.text.Spannable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import com.sunc.app.DataModel
import com.sunc.app.component.LoginModule
import com.sunc.base.BaseBindingActivity
import com.sunc.bean.LoginData
import com.sunc.bigfamily.databinding.ActivityLoginBinding
import com.sunc.db.SharedPreferencesHelper
import com.sunc.mvp.contract.LoginContract
import com.sunc.mvp.presenter.LoginPresenter
import com.sunc.utils.BlankUtils
import com.sunc.utils.getMainComponent
import com.sunc.utils.toast
import io.rong.imkit.RongIM
import io.rong.imlib.RongIMClient
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_title_bar.*
import javax.inject.Inject


class LoginActivity : BaseBindingActivity<ActivityLoginBinding>(), LoginContract.View {
    val TAG = "LoginActivity"
    @Inject lateinit var mPresenter: LoginPresenter

    override fun callback(model: DataModel<LoginData>) {
        if (model.isSuccess) {
            val user = model.data.user
            val token = model.data.token
            Log.d(TAG, "$user, $token")
            SharedPreferencesHelper.getInstance().saveData(SharedPreferencesHelper.CONFIG_TOKEN, token)
            connect(token)
        } else {
            toast(model.message)
        }
    }

    override fun createDataBinding(savedInstanceState: Bundle?): ActivityLoginBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun initView(savedInstanceState: Bundle?) {
        getMainComponent().plus(LoginModule(this)).inject(this)
        head_title.text = "登录"
        head_back.visibility = View.VISIBLE
        head_back.setOnClickListener {
            onBackPressed()
        }

        cb_password_visible.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                et_password.transformationMethod = PasswordTransformationMethod.getInstance()
            }
            et_password.postInvalidate()
            val text = et_password.text
            if (!BlankUtils.isBlank(text)) {
                Selection.setSelection(text as Spannable, text.length)
            }
        }
        btn_register.setOnClickListener {
            goToRegister()
        }
        btn_login.setOnClickListener {
            mPresenter.login(et_username.text.toString(), et_password.text.toString())
        }
    }

    override fun onDestroy() {
        mPresenter.unSubscribe()
        super.onDestroy()
    }

    private fun goToMain() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    private fun goToRegister() {
        startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
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

}
