package com.sec.common.ktx

import android.content.Context
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

/**
 * [OneTimeWorkRequestBuilder]扩展
 */
inline fun <reified W : ListenableWorker> Context.oneTimeWork(vararg pairs: Pair<String, Any?>) {
    val builder = Data.Builder()
    with(builder) {
        for ((key, value) in pairs) {
            when (value) {
                null -> putString(key, null)
                is Boolean -> putBoolean(key, value)
                is Byte -> putByte(key, value)
                is Double -> putDouble(key, value)
                is Float -> putFloat(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                is String -> putString(key, value)
            }
        }
    }
    WorkManager.getInstance(applicationContext).enqueue(
        OneTimeWorkRequestBuilder<W>().setInputData(builder.build()).build()
    )
}