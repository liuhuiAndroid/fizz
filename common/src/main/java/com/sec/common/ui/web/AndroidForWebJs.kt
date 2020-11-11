package com.sec.common.ui.web

import android.webkit.JavascriptInterface

internal class AndroidForWebJs(private val support: AndroidForWebSupport) {

    /**
     * 关闭页面
     */
    @JavascriptInterface
    fun closePage() {
        support.invokeClosePage()
    }

}