package com.sunc.bigfamily

import android.support.multidex.MultiDexApplication
import android.app.ActivityManager
import android.content.Context
import io.rong.imkit.RongIM


/**
 * Description:
 * Date: 2018-08-03 09:44
 * Author: suncheng
 */
class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        if (applicationInfo.packageName == getCurProcessName(applicationContext)) {
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