package com.sunc.bigfamily

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.sunc.base.BaseBindingActivity
import com.sunc.base.TabManager
import com.sunc.bigfamily.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    val WHICH_PAGE = "WHICH_PAGE"
    val TREE = "TREE"
    val CHAT = "CHAT"
    val MOMENT = "MOMENT"

    lateinit var mTabManager: TabManager

    override fun createDataBinding(savedInstanceState: Bundle?): ActivityMainBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun initView(savedInstanceState: Bundle?) {
        mTabManager = TabManager(this, supportFragmentManager, R.id.container)
        mTabManager.addTab(TREE, TreeFragment::class.java, null)
                .addTab(CHAT, TreeFragment::class.java, null)
                .addTab(MOMENT, TreeFragment::class.java, null)
        mTabManager.setOnTabChangedListener { tabId ->
            if (tabId != null) {
                this@MainActivity.onTabChanged(tabId)
            }
        }
        tv_tree.setOnClickListener {
            mTabManager.changeTab(TREE)
        }
        tv_chat.setOnClickListener {
            mTabManager.changeTab(CHAT)
        }
        tv_moment.setOnClickListener {
            mTabManager.changeTab(MOMENT)
        }
        val whichPage = intent.getStringExtra(WHICH_PAGE)
        if (TREE == whichPage || CHAT == whichPage || MOMENT == whichPage) {
            mTabManager.changeTab(whichPage)
        } else if (savedInstanceState != null) {
            mTabManager.restoreState(savedInstanceState)
        } else {
            mTabManager.changeTab(TREE)
        }
    }

    private fun onTabChanged(tabId: String) {
        tv_tree.isSelected = tabId == TREE
        tv_chat.isSelected = tabId == CHAT
        tv_moment.isSelected = tabId == MOMENT
    }

}
