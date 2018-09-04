package com.sunc.bigfamily

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunc.base.BaseBingingFragment
import com.sunc.bigfamily.databinding.FragmentTreeBinding

/**
 * Created by Administrator on 2018/6/11.
 */
class TreeFragment : BaseBingingFragment<FragmentTreeBinding>() {
    lateinit var mFragments: MutableList<ContactFragment>

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): FragmentTreeBinding {
        return FragmentTreeBinding.inflate(inflater!!, container, false)
    }

    override fun initView() {
        mFragments = ArrayList()
        mFragments.add(ContactFragment.newInstance())
        mFragments.add(ContactFragment.newInstance())
        var mAdapter = object : FragmentPagerAdapter(childFragmentManager) {
            private val titles = arrayOf("家谱", "家人")

            override fun getPageTitle(position: Int): CharSequence {
                return titles[position]
            }

            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> mFragments[0]
                    else -> mFragments[1]
                }
            }

            override fun getCount(): Int {
                return 2
            }
        }
        mBinding.viewPager.adapter = mAdapter
        mBinding.tab.setViewPager(mBinding.viewPager)
        mBinding.viewPager.offscreenPageLimit = 2
    }

}