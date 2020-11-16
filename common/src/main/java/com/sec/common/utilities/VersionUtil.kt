package com.sec.common.utilities

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

fun getAppVersionCode(context: Context): Long {
    var appVersionCode: Long = 0
    try {
        val packageInfo = context.applicationContext
            .packageManager
            .getPackageInfo(context.packageName, 0)
        appVersionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.longVersionCode
        } else {
            packageInfo.versionCode.toLong()
        }
    } catch (e: PackageManager.NameNotFoundException) {
    }
    return appVersionCode
}

fun getAppVersionName(context: Context): String {
    var appVersionName = ""
    try {
        val packageInfo = context.applicationContext
            .packageManager
            .getPackageInfo(context.packageName, 0)
        appVersionName = packageInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
    }
    return appVersionName
}