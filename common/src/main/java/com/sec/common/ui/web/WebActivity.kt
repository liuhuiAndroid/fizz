package com.sec.common.ui.web

import android.graphics.Color
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.sec.common.BaseActivity

class WebActivity : BaseActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val androidForWebJs = androidWebJs {
            onClosePage {

            }
        }
        webView = WebView(this)

        webView.apply {
            WebView.setWebContentsDebuggingEnabled(true)
            setBackgroundColor(Color.TRANSPARENT)
            background?.alpha = 0
            settings.apply {
                loadWithOverviewMode = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
                setAppCacheEnabled(true)
                cacheMode = WebSettings.LOAD_DEFAULT
                useWideViewPort = true
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            webViewClient = object : WebViewClient() {

            }
            addJavascriptInterface(androidForWebJs, "fizz")
        }
        // content.addView(webView)
    }

    override fun onResume() {
        webView.onResume()
        super.onResume()
    }

    override fun onPause() {
        webView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        try {
            // content.removeAllViews()
            webView.destroy()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

}