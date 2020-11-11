package com.sec.common.intents

import android.content.Intent

class ForResultCallback {

    private var okResult: ((Intent?) -> Unit)? = null
    private var cancelResult: (() -> Unit)? = null

    infix fun onOk(block: (Intent?) -> Unit) {
        okResult = block
    }

    infix fun onCancel(block: () -> Unit) {
        cancelResult = block
    }

    fun doOk(intent: Intent?) {
        okResult?.invoke(intent)
    }

    fun doCancel() {
        cancelResult?.invoke()
    }
}