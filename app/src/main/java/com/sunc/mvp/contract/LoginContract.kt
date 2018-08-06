package com.sunc.mvp.contract

import com.sunc.app.BaseModel
import rx.Observable


/**
 * Created by wing on 16-11-24.
 */
interface LoginContract {
    interface View {
        fun  callback()
    }

    interface Model {
        fun login(username: String, password: String): Observable<BaseModel>
    }

    interface Presenter {
        fun login(username: String, password: String)
    }
}