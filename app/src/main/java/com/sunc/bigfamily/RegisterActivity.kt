package com.sunc.bigfamily

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.sunc.base.BaseBindingActivity
import com.sunc.bigfamily.databinding.ActivityRegisterBinding
import com.sunc.utils.ValidateUtils
import com.sunc.utils.toast
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.layout_title_bar.*

class RegisterActivity : BaseBindingActivity<ActivityRegisterBinding>() {

    override fun createDataBinding(savedInstanceState: Bundle?): ActivityRegisterBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_register)
    }

    override fun initView(savedInstanceState: Bundle?) {
        head_title.text = "注册"
        head_back.visibility = View.VISIBLE
        head_back.setOnClickListener {
            onBackPressed()
        }
        btn_commit.setOnClickListener {
            register()
        }
    }

    private fun register() {
        if (et_username.text.isBlank()) {
            toast("请输入手机号码或者身份证号码")
            return
        }
        val username = et_username.text.toString()
        if (et_password.text.isBlank()) {
            toast("请输入密码")
            return
        }
        if (et_password_again.text.isBlank()) {
            toast("请输入再次密码")
            return
        }
        if (et_password.text.toString() != et_password_again.text.toString()) {
            toast("两次输入密码不一致")
            return
        }
        val password = et_password.text.toString()
        when {
            ValidateUtils.validPhoneNum("0", username) -> {

            }
            ValidateUtils.IDCardValidate(username) -> {

            }
            else -> {
                toast("请输入正确的手机号码或者身份证号码")
            }
        }

    }

}
