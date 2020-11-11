package com.sec.fizz.start

import android.content.Context
import androidx.startup.Initializer
import com.sec.common.BuildConfig
import com.sec.common.constant.MMKVConst
import com.sec.common.ktx.encodeKV
import com.tencent.mmkv.MMKV
import com.ut.device.UTDevice
import timber.log.Timber

class MMKVInitializer : Initializer<MMKVInitializer.Dependency> {

    class Dependency(context: Context) {
        init {
            MMKV.initialize(context)
        }
    }

    override fun create(context: Context): Dependency {
        return Dependency(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

}