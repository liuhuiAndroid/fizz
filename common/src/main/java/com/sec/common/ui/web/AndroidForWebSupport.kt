package com.sec.common.ui.web

internal class AndroidForWebSupport{

    private var closePage: (() -> Unit)? = null

    fun invokeClosePage() {
        closePage?.invoke()
    }

    fun onClosePage(block: () -> Unit) {
        closePage = block
    }
}

internal inline fun androidWebJs(crossinline support: AndroidForWebSupport.() -> Unit): AndroidForWebJs {
    return AndroidForWebJs(AndroidForWebSupport().apply(support))
}