package com.sec.common.network

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.sec.common.data.FizzResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val ERROR_NET = -1000

class FizzRequest<T> {

    private lateinit var loader: suspend () -> FizzResponse<T>
    private var responseResult: ((FizzResponse<T>) -> Unit)? = null

    infix fun loader(block: suspend () -> FizzResponse<T>) {
        loader = block
    }

    infix fun onResult(block: (FizzResponse<T>) -> Unit) {
        responseResult = block
    }

    fun launchRequest(scope: CoroutineScope) {
        scope.launch {
            try {
                val response = withContext(IO) {
                    loader()
                }
                responseResult?.invoke(response)
            } catch (e: Exception) {
                e.printStackTrace()
                responseResult?.invoke(
                    FizzResponse(ERROR_NET)
                )
            }
        }
    }
}

inline fun <T> ViewModel.launchRequest(crossinline request: FizzRequest<T>.() -> Unit) {
    FizzRequest<T>().apply(request).launchRequest(viewModelScope)
}

inline fun <T> FragmentActivity.launchRequest(crossinline request: FizzRequest<T>.() -> Unit) {
    FizzRequest<T>().apply(request).launchRequest(lifecycleScope)
}