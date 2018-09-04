package com.sunc.bigfamily

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunc.base.BaseBingingFragment
import com.sunc.bigfamily.databinding.FragmentMyBinding
import kotlinx.android.synthetic.main.layout_title_bar.view.*

/**
 * Created by Administrator on 2018/6/11.
 */
class MyFragment : BaseBingingFragment<FragmentMyBinding>() {

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): FragmentMyBinding {
        return FragmentMyBinding.inflate(inflater!!, container, false)
    }

    override fun initView() {
        initHeader(mBinding.toolBar)
    }

    private fun initHeader(toolbar: Toolbar) {
        val headerView = layoutInflater.inflate(R.layout.layout_title_bar, toolbar, false)
        val lp  = headerView.layoutParams as  Toolbar.LayoutParams
        lp.gravity = Gravity.CENTER
        toolbar.addView(headerView, lp)
        headerView.head_title.text = "我的"
    }

}