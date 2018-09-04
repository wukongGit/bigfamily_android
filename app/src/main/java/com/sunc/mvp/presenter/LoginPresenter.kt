package com.sunc.mvp.presenter

import android.util.Log
import com.sunc.base.BasePresenter
import com.sunc.bean.Member
import com.sunc.mvp.contract.LoginContract
import com.sunc.mvp.model.LoginModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Administrator on 2018/6/25.
 */
class LoginPresenter
@Inject constructor(private val mModel: LoginModel,
                    private val mView: LoginContract.View) : LoginContract.Presenter, BasePresenter() {
    val TAG = "LoginPresenter"

    override fun register(username: String, password: String) {
        addSubscription(mModel.register(username, password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.callback(res)
                }, {
                    throwable ->
                    Log.e(TAG, throwable.message)
                }))
    }

    override fun login(username: String, password: String) {
        addSubscription(mModel.login(username, password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    mView.callback(res)
                }, {
                    throwable ->
                    Log.e(TAG, throwable.message)
                }))
    }

}