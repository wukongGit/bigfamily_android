package com.sunc.mvp.contract

import com.sunc.app.DataModel
import com.sunc.bean.LoginData
import com.sunc.bean.Member
import rx.Observable


/**
 * Created by wing on 16-11-24.
 */
interface LoginContract {
    interface View {
        fun  callback(model: DataModel<LoginData>)
    }

    interface Model {
        fun login(username: String, password: String): Observable<DataModel<LoginData>>
        fun register(username: String, password: String):  Observable<DataModel<LoginData>>
    }

    interface Presenter {
        fun login(username: String, password: String)
        fun register(username: String, password: String)
    }
}