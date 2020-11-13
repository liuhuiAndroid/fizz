package com.sec.common.network

import com.sec.common.constant.HeaderConst
import com.sec.common.data.FizzResponse
import com.sec.common.data.response.OfficialNewsJson
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PublicApi {

    /**
     *官方消息未读数量
     */
    @Headers(HeaderConst.HEADER_ES)
    @GET("GetPublicRequest")
    suspend fun officialNewsList(
        @Query("Param") param: String,
        @Query("Fun") func: String = "SysInfo",
        @Query("ServiceType") type: String = "PostMg"
    ): FizzResponse<List<OfficialNewsJson>>

}