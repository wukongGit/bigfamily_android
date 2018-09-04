package com.sunc.bigfamily

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.sunc.base.BaseBingingFragment
import com.sunc.bean.Member
import com.sunc.bigfamily.databinding.FragmentContactBinding
import com.sunc.utils.PinyinComparator
import io.rong.imkit.RongIM
import io.rong.imkit.tools.CharacterParser
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Administrator on 2018/6/11.
 */
class ContactFragment : BaseBingingFragment<FragmentContactBinding>() {
    private var mFriendList: MutableList<Member>? = null
    private var mFriendListAdapter: FriendListAdapter? = null
    private var mCharacterParser: CharacterParser? = null
    private var mPinyinComparator: PinyinComparator? = null

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): FragmentContactBinding {
        return FragmentContactBinding.inflate(inflater!!, container, false)
    }

    override fun initView() {
        initData()
        var list = ArrayList<Member>()
        for (i in 1 .. 10) {
            var member = Member()
            member.name = "qioafeng $i"
            member.nameSpelling = "qioafeng $i"
            list.add(member)
        }
        for (i in 1 .. 10) {
            var member = Member()
            member.name = "xuzhu $i"
            member.nameSpelling = "xuzhu $i"
            list.add(member)
        }
        for (i in 1 .. 10) {
            var member = Member()
            member.name = "duanyu $i"
            member.nameSpelling = "duanyu $i"
            list.add(member)
        }
        updateFriendsList(list)
    }

    private fun initData() {
        val headerView = layoutInflater.inflate(R.layout.item_contact_list_header, null)
        mBinding.listview.addHeaderView(headerView)
        mBinding.showNoFriend.visibility = View.VISIBLE
        mFriendList = ArrayList<Member>()
        val adapter = FriendListAdapter(activity, mFriendList)
        mBinding.listview.adapter = adapter
        //实例化汉字转拼音类
        mCharacterParser = CharacterParser.getInstance()
        mPinyinComparator = PinyinComparator.getInstance()
        mBinding.sideBar.setOnTouchingLetterChangedListener { s ->
            //该字母首次出现的位置
            val position = mFriendListAdapter?.getPositionForSection(s[0].toInt())
            if (position != -1) {
                mBinding.listview.setSelection(position!!)
            }
        }
        mBinding.listview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            RongIM.getInstance().startPrivateChat(activity, "1002", "标题")
        }
    }

    private fun updateFriendsList(friendsList: List<Member>?) {
        //updateUI fragment初始化和好友信息更新时都会调用,isReloadList表示是否是好友更新时调用
        var isReloadList = false
        if (mFriendList != null && mFriendList!!.size > 0) {
            mFriendList?.clear()
            isReloadList = true
        }
        mFriendList = friendsList as MutableList<Member>?
        if (mFriendList != null && mFriendList!!.size > 0) {
            handleFriendDataForSort()
            mBinding.showNoFriend.visibility = View.GONE
        } else {
            mBinding.showNoFriend.visibility = View.VISIBLE
        }

        // 根据a-z进行排序源数据
        Collections.sort(mFriendList, mPinyinComparator)
        if (isReloadList) {
            mBinding.sideBar.visibility = View.VISIBLE
            mFriendListAdapter?.updateListView(mFriendList)
        } else {
            mBinding.sideBar.visibility = View.VISIBLE
            mFriendListAdapter = FriendListAdapter(activity, mFriendList)

            mBinding.listview.adapter = mFriendListAdapter

        }
    }

    private fun handleFriendDataForSort() {
        for (friend in mFriendList!!) {
            val letters = replaceFirstCharacterWithUppercase(friend.nameSpelling)
            friend.letters = letters
        }
    }

    private fun replaceFirstCharacterWithUppercase(spelling: String): String {
        return if (!TextUtils.isEmpty(spelling)) {
            val first = spelling[0]
            var newFirst = first
            if (first in 'a'..'z') {
                newFirst -= 32
            }
            spelling.replaceFirst(first.toString().toRegex(), newFirst.toString())
        } else {
            "#"
        }
    }

    companion object {
        fun newInstance(): ContactFragment {
            return ContactFragment()
        }
    }

}