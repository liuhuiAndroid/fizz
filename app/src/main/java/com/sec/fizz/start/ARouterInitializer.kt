package com.sec.fizz.start

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import com.sec.fizz.BuildConfig
import timber.log.Timber

class ARouterInitializer : Initializer<ARouterInitializer.Dependency> {

    class Dependency(context: Context) {
        init {
            if (BuildConfig.DEBUG) {
                // 打印日志
                ARouter.openLog()
                // 开启调试模式
                ARouter.openDebug()
            }
            // 尽可能早，推荐在Application中初始化
            ARouter.init(context.applicationContext as Application?)
        }
    }

    override fun create(context: Context): Dependency {
        return Dependency(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

}