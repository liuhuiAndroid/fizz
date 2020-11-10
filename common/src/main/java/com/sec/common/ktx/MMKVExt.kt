package com.sec.common.ktx

import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 * 存储键值对
 */
fun encodeKV(key: String, value: Any?) {
    when (value) {
        null -> {
            //do nothing
        }
        is Int -> MMKV.defaultMMKV().encode(key, value)
        is Long -> MMKV.defaultMMKV().encode(key, value)
        is Double -> MMKV.defaultMMKV().encode(key, value)
        is Float -> MMKV.defaultMMKV().encode(key, value)
        is Boolean -> MMKV.defaultMMKV().encode(key, value)
        is String -> MMKV.defaultMMKV().encode(key, value)
        is Parcelable -> MMKV.defaultMMKV().encode(key, value)
    }
}

fun decode(key: String, default: String = ""): String = MMKV.defaultMMKV().decodeString(key, default)

fun decode(key: String, default: Int = 0): Int = MMKV.defaultMMKV().decodeInt(key, default)

fun decode(key: String, default: Long = 0): Long = MMKV.defaultMMKV().decodeLong(key, default)

fun decode(key: String, default: Double = 0.0): Double = MMKV.defaultMMKV().decodeDouble(key, default)

fun decode(key: String, default: Float = 0F): Float = MMKV.defaultMMKV().decodeFloat(key, default)

fun decode(key: String, default: Boolean = false): Boolean = MMKV.defaultMMKV().decodeBool(key, default)

fun removeByKey(key: String) { MMKV.defaultMMKV().removeValueForKey(key) }

