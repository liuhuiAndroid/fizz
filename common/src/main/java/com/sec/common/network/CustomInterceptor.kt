package com.sec.common.network

import android.os.Build
import com.sec.common.constant.MMKVConst
import com.sec.common.ktx.decode
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 自定义拦截器
 */
class CustomInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(createNewRequest(request))
    }

    private fun createNewRequest(request: Request): Request {
        val builder = request.newBuilder()
        builder
            .header("OSVersion", Build.VERSION.SDK_INT.toString())
            .header("appVersion", decode(MMKVConst.VERSION_CODE, 0).toString())
            .header("DeviceID", decode(MMKVConst.DEVICE_ID, "none"))
        return builder.build()
    }

}