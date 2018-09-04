package com.sunc.bigfamily

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.SystemClock
import com.sunc.base.BaseBindingActivity
import com.sunc.base.TabManager
import com.sunc.bigfamily.databinding.ActivityMainBinding
import com.sunc.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    val WHICH_PAGE = "WHICH_PAGE"
    val CHAT = "CHAT"
    val TREE = "TREE"
    val MOMENT = "MOMENT"
    val MY = "MY"

    lateinit var mTabManager: TabManager
    private var mBackTime: Long = 0

    override fun createDataBinding(savedInstanceState: Bundle?): ActivityMainBinding {
        return DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun initView(savedInstanceState: Bundle?) {
        mTabManager = TabManager(this, supportFragmentManager, R.id.container)
        mTabManager.addTab(CHAT, ChatFragment::class.java, null)
                .addTab(TREE, TreeFragment::class.java, null)
                .addTab(MOMENT, MomentFragment::class.java, null)
                .addTab(MY, MyFragment::class.java, null)
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
        tv_my.setOnClickListener {
            mTabManager.changeTab(MY)
        }
        val whichPage = intent.getStringExtra(WHICH_PAGE)
        if (TREE == whichPage || CHAT == whichPage || MOMENT == whichPage || MY == whichPage) {
            mTabManager.changeTab(whichPage)
        } else if (savedInstanceState != null) {
            mTabManager.restoreState(savedInstanceState)
        } else {
            mTabManager.changeTab(CHAT)
        }
    }

    private fun onTabChanged(tabId: String) {
        tv_chat.isSelected = tabId == CHAT
        tv_tree.isSelected = tabId == TREE
        tv_moment.isSelected = tabId == MOMENT
        tv_my.isSelected = tabId == MY
    }


    override fun onBackPressed() {
        val time = SystemClock.elapsedRealtime()
        if (time - mBackTime > 2000) {
            toast("再按一次退出程序")
            mBackTime = time
            return
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        super.onBackPressed()
    }

}
