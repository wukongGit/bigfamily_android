package com.sunc.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by wing on 16-11-24.
 */
abstract class BaseBingingFragment<B:ViewDataBinding> : Fragment(){
    lateinit var mBinding : B
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = createDataBinding(inflater,container,savedInstanceState)
        initView()
        return mBinding.root

    }

    abstract fun  createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): B

    abstract fun initView()

}