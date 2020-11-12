package com.sec.common.network

import com.sec.common.data.FizzResponse
import com.sec.common.data.TestJson
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CommonApi {

    @GET("testRequest")
    suspend fun testJson(@QueryMap map: Map<String, String>): FizzResponse<TestJson>

}