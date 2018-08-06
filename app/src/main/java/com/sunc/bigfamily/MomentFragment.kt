package com.sunc.bigfamily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunc.base.BaseBingingFragment
import com.sunc.bigfamily.databinding.FragmentMomentBinding
import io.rong.imkit.RongIM
import kotlinx.android.synthetic.main.fragment_moment.*

/**
 * Created by Administrator on 2018/6/11.
 */
class MomentFragment : BaseBingingFragment<FragmentMomentBinding>() {

    override fun createDataBinding(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): FragmentMomentBinding {
        return FragmentMomentBinding.inflate(inflater!!, container, false)
    }

    override fun initView() {
        mBinding.go.setOnClickListener {
            val userId = user_id.text.toString()
            RongIM.getInstance().startPrivateChat(context, userId, "tt")
        }
    }

}