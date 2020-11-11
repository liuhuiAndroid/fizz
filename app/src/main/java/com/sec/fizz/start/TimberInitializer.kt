package com.sec.fizz.start

import android.content.Context
import androidx.startup.Initializer
import com.sec.fizz.BuildConfig
import timber.log.Timber

class TimberInitializer : Initializer<TimberInitializer.Dependency> {

    class Dependency(context: Context) {
        init {
            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
        }
    }

    override fun create(context: Context): Dependency {
        return Dependency(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

}