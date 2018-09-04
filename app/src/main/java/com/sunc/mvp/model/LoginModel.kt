package com.sunc.mvp.model

import com.sunc.api.FamilyApi
import com.sunc.app.BaseModel
import com.sunc.app.DataModel
import com.sunc.bean.LoginData
import com.sunc.bean.Member
import com.sunc.mvp.contract.LoginContract
import rx.Observable
import javax.inject.Inject

/**
 * Created by Administrator on 2018/6/25.
 */
class LoginModel
@Inject constructor(private val api: FamilyApi) : LoginContract.Model {
    override fun register(username: String, password: String): Observable<DataModel<LoginData>> {
        return api.register(username, password)
    }

    override fun login(username: String, password: String): Observable<DataModel<LoginData>> {
        return api.login(username, password)
    }
}