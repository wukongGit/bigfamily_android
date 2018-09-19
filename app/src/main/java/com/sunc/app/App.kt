package com.sunc.app

import android.support.multidex.MultiDexApplication
import android.app.ActivityManager
import android.content.Context
import com.sunc.db.SharedPreferencesHelper
import io.rong.imkit.RongIM
import javax.inject.Inject


/**
 * Description:
 * Date: 2018-08-03 09:44
 * Author: suncheng
 */
class App : MultiDexApplication() {
    companion object {
        lateinit var instance: App
    }
    init {
        instance = this
    }

    @Inject
    lateinit var apiComponent: ApiComponent

    override fun onCreate() {
        super.onCreate()
        DaggerApiComponent.builder().apiModule(ApiModule()).appModule(AppModule(this)).build().inject(this)
        if (applicationInfo.packageName == getCurProcessName(applicationContext)) {
            SharedPreferencesHelper.init(this)
            SealAppContext.init(this)
            RongIM.init(this)
        }
    }

    private fun getCurProcessName(context: Context): String? {
        val pid = android.os.Process.myPid()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.runningAppProcesses
                .firstOrNull { it.pid == pid }
                ?.processName
    }
}