package com.sec.common.utilities

import com.alibaba.android.arouter.launcher.ARouter
import com.sec.common.data.UserInfo
import com.sec.common.ktx.object2Json
import com.sec.common.provider.PassportService

object UserUtil {

    fun setUserInfo(userInfo: UserInfo) {
        ARouter.getInstance().navigation(PassportService::class.java)
            .insertUserInfo(object2Json(userInfo))
    }

    fun cleanUserInfo() {
        ARouter.getInstance().navigation(PassportService::class.java)
            .insertUserInfo("")
    }
}