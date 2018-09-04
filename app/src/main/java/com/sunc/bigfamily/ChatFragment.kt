package com.sunc.bigfamily

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunc.base.BaseBingingFragment
import com.sunc.bigfamily.databinding.FragmentChatBinding
import io.rong.imkit.RongContext
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.layout_title_bar.view.*

/**
 * Created by Administrator on 2018/6/11.
 */
class ChatFragment : BaseBingingFragment<FragmentChatBinding>() {
    private var mConversationListFragment: ConversationListFragment? = null
    private var mConversationsTypes: Array<Conversation.ConversationType>? = null

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): FragmentChatBinding {
        return FragmentChatBinding.inflate(inflater!!, container, false)
    }

    override fun initView() {
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, initConversationList())
        fragmentTransaction.commit()
        initHeader(mBinding.toolBar)
    }

    private fun initHeader(toolbar: Toolbar) {
        val headerView = layoutInflater.inflate(R.layout.layout_title_bar, toolbar, false)
        val lp  = headerView.layoutParams as  Toolbar.LayoutParams
        lp.gravity = Gravity.CENTER
        toolbar.addView(headerView, lp)
        headerView.head_title.text = "家常"
    }

    private fun initConversationList(): Fragment {
        if (mConversationListFragment == null) {
            val listFragment = ConversationListFragment()
            listFragment.setAdapter(ConversationListAdapterEx(RongContext.getInstance()))
            val uri = Uri.parse("rong://" + activity.applicationInfo.packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                    .build()
            mConversationsTypes = arrayOf(Conversation.ConversationType.PRIVATE, Conversation.ConversationType.GROUP, Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE, Conversation.ConversationType.SYSTEM)
            listFragment.uri = uri
            mConversationListFragment = listFragment
            return listFragment
        } else {
            return mConversationListFragment as ConversationListFragment
        }
    }

}