package com.sunc.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sunc.bigfamily.R
import com.sunc.utils.StatusBarUtils.setWindowStatusBarColor

/**
 * Created by wing on 16-11-24.
 */
abstract class BaseBindingActivity<B : ViewDataBinding> : AppCompatActivity() {


    lateinit var mBinding: B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowStatusBarColor(this, R.color.main_color_normal)
        mBinding = createDataBinding(savedInstanceState)

        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun  createDataBinding(savedInstanceState: Bundle?): B


}