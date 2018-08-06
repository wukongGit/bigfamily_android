package com.sunc.app.component

import com.sunc.bigfamily.LoginActivity
import com.sunc.mvp.contract.LoginContract
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

/**
 * Created by Administrator on 2018/6/25.
 */
@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent {
    fun inject(activity: LoginActivity)
}

@Module
class LoginModule(private val mView: LoginContract.View){
    @Provides
    fun getView() = mView
}