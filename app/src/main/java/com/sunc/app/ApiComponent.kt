package com.sunc.app

import com.sunc.app.component.LoginComponent
import com.sunc.app.component.LoginModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by wing on 16-11-23.
 */
@Singleton
@Component(modules = arrayOf(ApiModule::class))
interface ApiComponent{

    fun inject(app: App)

    fun plus(module: LoginModule): LoginComponent
}

