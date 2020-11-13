package com.sec.common.network

import com.sec.common.json.NullJsonFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Api服务
 */
class ApiService private constructor() {

    companion object {

        private val retrofit: Retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            val clientBuild =
                OkHttpClient.Builder()
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .addInterceptor(CustomInterceptor())

            clientBuild.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

            val build = Moshi.Builder()
                .add(NullJsonFactory.STANDARD)
                .add(NullJsonFactory.COLLECTION)
                .build()
            Retrofit.Builder()
                .baseUrl("http://centanet.com.cn/")
                .addConverterFactory(MoshiConverterFactory.create(build))
                .client(clientBuild.build())
                .build()
        }

        val commonApi: CommonApi by lazy {
            retrofit.create(CommonApi::class.java)
        }

        val publicApi: PublicApi by lazy {
            retrofit.create(PublicApi::class.java)
        }

    }
}