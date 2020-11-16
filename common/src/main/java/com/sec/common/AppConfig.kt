package com.sec.common

import android.app.Application
import com.sec.common.constant.MMKVConst
import com.sec.common.ktx.encodeKV
import com.ut.device.UTDevice

class AppConfig {

    companion object {

        fun initApplication(application: Application, versionCode: Long, versionName: String) {
            encodeKV(MMKVConst.VERSION_CODE, versionCode)
            encodeKV(MMKVConst.VERSION_NAME, versionName)
            encodeKV(MMKVConst.DEVICE_ID, UTDevice.getUtdid(application))
            // 测试
            encodeKV(MMKVConst.CITY_CODE, "022")
            encodeKV(MMKVConst.CITY_EN, "tj")
            encodeKV(MMKVConst.CITY_NAME, "天津")
            encodeKV(MMKVConst.API_SECONDHAND, "http://114.80.110.197/testmobileapi/json/reply/")
        }
    }
}