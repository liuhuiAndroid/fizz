package com.sec.common.ktx

import com.sec.common.json.NullJsonFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

val moShi: Moshi
    get() = Moshi.Builder().add(NullJsonFactory.STANDARD).add(NullJsonFactory.COLLECTION).build()

/**
 * 解析json数组
 */
inline fun <reified E> parseJsonArray(json: String?): List<E> {
    val result = mutableListOf<E>()
    if (json.isNullOrEmpty()) return result
    try {
        val type = Types.newParameterizedType(List::class.java, E::class.java)
        val jsonAdapter: JsonAdapter<List<E>> = moShi.adapter(type)
        jsonAdapter.fromJson(json)?.run {
            result.addAll(this)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return result
}

/**
 * 解析json对象
 */
inline fun <reified E> parseJsonObject(json: String?): E? {
    if (json.isNullOrEmpty()) return null
    try {
        val jsonAdapter = moShi.adapter(E::class.java)
        return jsonAdapter.fromJson(json)
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}

/**
 *解析jsonMap
 */
inline fun <reified V> parseJsonMap(json: String?): MutableMap<String, V?> {
    val mutableMapOf = mutableMapOf<String, V?>()
    try {
        val jsonObject = JSONObject(json ?: "")
        val iterator = jsonObject.keys()
        var key: String
        while (iterator.hasNext()) {
            key = iterator.next()
            key.notEmpty {
                mutableMapOf[it] = jsonObject.get(key) as V
            }
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return mutableMapOf
}

/**
 * String 转 JSONObject
 */
fun String?.toJSONObject(): JSONObject = try {
    JSONObject(this ?: "")
} catch (e: JSONException) {
    JSONObject()
}

/**
 * List<E> to Json
 */
inline fun <reified E> listToJson(list: List<E>): String {
    val type = Types.newParameterizedType(List::class.java, E::class.java)
    val jsonAdapter: JsonAdapter<List<E>> = Moshi.Builder().build().adapter(type)
    return jsonAdapter.toJson(list)
}

/**
 * 对象转json
 */
inline fun <reified E> object2Json(obj: E?): String {
    val jsonAdapter = Moshi.Builder().build().adapter(E::class.java)
    return jsonAdapter.toJson(obj)
}

/**
 * 构建json字符串
 */
fun jsonOf(vararg pairs: Pair<String, Any?>): String {
    val map = mutableMapOf<String, Any>()
    for ((key, value) in pairs) {
        if (value != null) {
            map[key] = value
        }
    }
    return object2Json(map)
}

fun String?.isJson(): Boolean {
    if (isNullOrEmpty()) return false
    return try {
        JSONObject(this)
        true
    } catch (e: Exception) {
        isJsonArray()
    }
}

/**
 *判断是否是json数组
 */
fun String?.isJsonArray(): Boolean {
    return try {
        JSONArray(this)
        true
    } catch (e: Exception) {
        false
    }
}
