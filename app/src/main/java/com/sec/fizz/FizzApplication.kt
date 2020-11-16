package com.sec.fizz

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.sec.common.AppConfig
import com.sec.common.utilities.getAppVersionCode
import com.sec.common.utilities.getAppVersionName

private lateinit var INSTANCE: Application

/**
 * 获取全局Context，在代码的任意位置都可以调用，随时都能获取到全局Context对象。
 *
 * @return 全局Context对象。
 */
object AppContext : ContextWrapper(INSTANCE)

class FizzApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        //TODO
        AppConfig.initApplication(
            this,
            getAppVersionCode(this),
            getAppVersionName(this)
        )

        BuildConfig.BUILD_TYPE
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}