package com.sec.common.data.response

import com.squareup.moshi.JsonClass

/**
 * 系统消息
 */
@JsonClass(generateAdapter = true)
data class OfficialNewsJson(
    val SysInfoId: String?,
    val Title: String?,
    val InfoContent: String?,
    val PushTime: Long = 0,
    val infoType: Int?,
    val converImgUrl: String?,
    val description: String?
)
