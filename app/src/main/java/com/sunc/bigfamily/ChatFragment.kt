package com.sunc.bigfamily

import android.content.Context
import android.content.pm.ApplicationInfo
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunc.base.BaseBingingFragment
import com.sunc.bigfamily.databinding.FragmentChatBinding
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imlib.model.Conversation

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
    }

    private fun initConversationList(): Fragment {
        if (mConversationListFragment == null) {
            val listFragment = ConversationListFragment()
            //listFragment.setAdapter(ConversationListAdapterEx(RongContext.getInstance()))
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