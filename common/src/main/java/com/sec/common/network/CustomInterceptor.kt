package com.sec.common.network

import android.os.Build
import com.sec.common.constant.HeaderConst
import com.sec.common.constant.MMKVConst
import com.sec.common.ktx.decode
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 自定义拦截器
 */
class CustomInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var baseUrl = ""
        request.headers.forEach {
            baseUrl = when ("${it.first}:${it.second}") {
                HeaderConst.HEADER_ES -> decode(MMKVConst.API_SECONDHAND, "")
                else -> ""
            }
        }
        val newBaseUrl = baseUrl
        return if (newBaseUrl.isEmpty())
            chain.proceed(createNewRequest(request, false))
        else {
            val urlBuilder = baseUrl.toHttpUrl().newBuilder()
            for (pathSegment in request.url.pathSegments) {
                urlBuilder.addPathSegment(pathSegment)
            }
            urlBuilder.encodedQuery(request.url.encodedQuery)
            chain.proceed(createNewRequest(request.newBuilder().url(urlBuilder.build()).build()))
        }
    }

    private fun createNewRequest(request: Request, needCity: Boolean = true): Request {
        val builder = request.newBuilder()
        builder.removeHeader(HeaderConst.HEADER_KEY)
        val cityEn = decode(MMKVConst.CITY_EN, "")
        if ("GET".equals(request.method, true)) {
            val urlBuilder = request.url.newBuilder()
            urlBuilder.addQueryParameter("platform", "android")
            if (needCity && cityEn.isNotEmpty()) {
                urlBuilder.addQueryParameter("cityen", cityEn)
            }
            builder.url(urlBuilder.build())
        } else {
            builder.header("platform", "android")
            if (needCity && cityEn.isNotEmpty()) {
                builder.header("cityen", cityEn)
            }
        }
        builder
            .header("OSVersion", Build.VERSION.SDK_INT.toString())
            .header("appVersion", decode(MMKVConst.VERSION_CODE, 0).toString())
            .header("DeviceID", decode(MMKVConst.DEVICE_ID, "none"))
        return builder.build()
    }

}