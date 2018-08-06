package com.sunc.mvp.presenter

import com.sunc.base.BasePresenter
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

    override fun login(username: String, password: String) {
        addSubscription(mModel.login(username, password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    res ->
                    if (res.isSuccess()) {
                        mView.callback()
                    }
                }, {}))
    }
}