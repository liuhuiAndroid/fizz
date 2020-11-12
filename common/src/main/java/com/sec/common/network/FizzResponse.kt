package com.sec.common.network

import com.sec.common.data.FizzResponse

fun <T> respond(
    response: FizzResponse<T>,
    normal: (result: T) -> Unit,
    error: (response: FizzResponse<T>) -> Unit = {}
) {
    val result = response.Result
    if (result != null && response.ResultNo == 0) {
        normal(result)
    } else {
        error(response)
    }
}