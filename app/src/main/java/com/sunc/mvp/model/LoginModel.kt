package com.sunc.mvp.model

import com.sunc.api.FamilyApi
import com.sunc.app.BaseModel
import com.sunc.mvp.contract.LoginContract
import rx.Observable
import javax.inject.Inject

/**
 * Created by Administrator on 2018/6/25.
 */
class LoginModel
@Inject constructor(private val api: FamilyApi) : LoginContract.Model {

    override fun login(username: String, password: String): Observable<BaseModel> {
        return api.login(username, password)
    }
}