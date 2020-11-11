package com.sec.common.ktx

/**
 * sealed class 使用 when 语句时获得编译器提示
 */
val <T> T.exhaustive: T
    get() = this