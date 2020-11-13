package com.sec.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.sec.common.data.FizzResponse

/**
 * 接口响应处理
 */
class ResponseHandler<T> {

    private var success: ((T) -> Unit)? = null
    private var fail: ((errorCode: Int) -> Unit)? = null

    infix fun onSuccess(block: (T) -> Unit) {
        success = block
    }

    infix fun onFail(block: (errorCode: Int) -> Unit) {
        fail = block
    }

    fun handle(response: FizzResponse<T>) {
        if (0 == response.ResultNo && response.Result != null) {
            success?.invoke(response.Result)
        } else {
            fail?.invoke(response.ResultNo)
        }
    }
}

inline fun <T> LiveData<FizzResponse<T>>.handle(
    owner: LifecycleOwner,
    crossinline responseHandler: ResponseHandler<T>.() -> Unit
) {
    observe(owner, Observer {
        ResponseHandler<T>().apply(responseHandler).handle(it)
    })
}