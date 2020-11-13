package com.sec.common.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.sec.common.constant.RoutePath
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

@Route(path = RoutePath.JSON_SERVICE)
class JsonSerializationService : SerializationService {

    private lateinit var moshi: Moshi

    override fun init(context: Context?) {
        moshi = Moshi.Builder().build()
    }

    override fun <T : Any?> json2Object(input: String, clazz: Class<T>): T? {
        val adapter = moshi.adapter<T>(clazz)
        return adapter.fromJson(input)
    }

    override fun object2Json(instance: Any): String {
        val adapter = moshi.adapter(instance.javaClass)
        return adapter.toJson(instance)
    }

    override fun <T : Any?> parseObject(input: String, clazz: Type): T? {
        val adapter = moshi.adapter<T>(clazz)
        return adapter.fromJson(input)
    }

}