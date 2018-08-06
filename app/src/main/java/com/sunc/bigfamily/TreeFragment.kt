package com.sunc.bigfamily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunc.base.BaseBingingFragment
import com.sunc.bigfamily.databinding.FragmentTreeBinding

/**
 * Created by Administrator on 2018/6/11.
 */
class TreeFragment : BaseBingingFragment<FragmentTreeBinding>() {

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): FragmentTreeBinding {
        return FragmentTreeBinding.inflate(inflater!!, container, false)
    }

    override fun initView() {

    }

}