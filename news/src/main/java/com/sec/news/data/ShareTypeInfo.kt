package com.sec.news.data

data class ShareTypeInfo(
    val mShareType: Int
){
   companion object{
       const val friend = 0 //微信好友
       const val moments = 1 //微信朋友圈
   }
}
