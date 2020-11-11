package com.sec.common.ui.web

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.sec.common.BaseActivity

/**
 * https://blog.csdn.net/cuo9958/article/details/50338823
 */
abstract class AbsWebViewActivity : BaseActivity() {

    abstract fun createWebView(): WebView

    abstract fun createProgressBar(): ProgressBar

    override fun onResume() {
        super.onResume()
        initWebView()
    }

    @SuppressLint("JavascriptInterface")
    private fun initWebView() {
        createWebView().apply {
            setBackgroundColor(
                ContextCompat.getColor(this@AbsWebViewActivity, android.R.color.transparent)
            )
            setBackgroundResource(android.R.color.transparent)
            settings.apply {
                // 开启前端代码和Android本地代码互调
                javaScriptEnabled = true
                // 设置缓存模式
                cacheMode = WebSettings.LOAD_DEFAULT
                allowUniversalAccessFromFileURLs = true
                // 解决对某些标签的不支持出现白屏
                domStorageEnabled = true
                useWideViewPort = true
                // 允许放大缩小
                builtInZoomControls = true
                displayZoomControls = false
                // 不允许加载不安全的链接
                mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
                //水平滚动条不显示
                isHorizontalScrollBarEnabled = false
                //垂直滚动条不显示
                isVerticalScrollBarEnabled = false
            }
            addJavascriptInterface(onJavaScriptInterface(), "WebBridge")
            webViewClient = mWebViewClient
            webChromeClient = mWebChromeClient
        }
    }

    /**
     * Android和Js交互
     */
    abstract fun onJavaScriptInterface(): Any

    private val mWebViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }

    private val mWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            if (newProgress == 100) {
                createProgressBar().visibility = View.GONE
            } else {
                if (createProgressBar().visibility == View.GONE) {
                    createProgressBar().visibility = View.VISIBLE
                }
                createProgressBar().progress = newProgress
            }
            super.onProgressChanged(view, newProgress)
        }
    }

    /**
     * 加载网页
     */
    fun loadUrl(url: String) {
        createWebView().loadUrl(url)
    }

    override fun onDestroy() {
        createWebView().apply {
            loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            clearHistory()
            (parent as ViewGroup).removeView(this)
        }
        super.onDestroy()
    }

}