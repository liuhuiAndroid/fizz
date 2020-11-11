package com.sec.common.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class UserInfo(
    val UserId: String,
    val NickName: String,
    val Phone: String,
    val UserPhotoUrl: String,
    val UserToken: String
) : Parcelable