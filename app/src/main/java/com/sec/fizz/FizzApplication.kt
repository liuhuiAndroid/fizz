package com.sec.fizz

import android.app.Application
import com.sec.common.AppConfig

class FizzApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppConfig.initApplication(
            this,
            resources.getInteger(R.integer.version_code),
            resources.getString(R.string.version_name)
        )
    }
}