package com.sunc.bigfamily

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Selection
import android.text.Spannable
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.sunc.base.BaseBindingActivity
import com.sunc.bigfamily.databinding.ActivityLoginBinding
import com.sunc.utils.BlankUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_title_bar.*



class LoginActivity : BaseBindingActivity<ActivityLoginBinding>() {

    override fun createDataBinding(savedInstanceState: Bundle?): ActivityLoginBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun initView(savedInstanceState: Bundle?) {
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
//            val intent = Intent()
//            intent.setClass(this@LoginActivity, RegisterActivity::class.java)
//            startActivity(intent)
        }
        btn_login.setOnClickListener {
//            val qb = QueryBuilder<FamilyUser>(FamilyUser::class.java)
//                    .whereEquals("username", et_username.text.toString())
//                    .whereAppendAnd()
//                    .whereEquals("password", et_password.text.toString())
//            val users = LiteOrmHelper.getInstance(this).query<FamilyUser>(qb)
//            if (users != null && !users.isEmpty()) {
//                val intent = Intent()
//                intent.setClass(this@LoginActivity, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            } else {
//                toast("用户名或者密码有误")
//            }
        }
    }

}
