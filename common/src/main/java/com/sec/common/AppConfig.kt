package com.sec.common

import android.app.Application
import com.sec.common.constant.MMKVConst
import com.sec.common.ktx.encodeKV
import com.tencent.mmkv.MMKV
import com.ut.device.UTDevice
import timber.log.Timber
import timber.log.Timber.DebugTree

class AppConfig {

    companion object {

        fun initApplication(application: Application, versionCode: Int, versionName: String) {
            encodeKV(MMKVConst.VERSION_CODE, versionCode)
            encodeKV(MMKVConst.VERSION_NAME, versionName)
            encodeKV(MMKVConst.DEVICE_ID, UTDevice.getUtdid(application))
        }
    }
}