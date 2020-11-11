package com.sec.common.ktx

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.showToast(text: String) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    } else {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }
}

fun View.showToast(text: String) {
    context.showToast(text)
}

fun Fragment.showToast(text: String) {
    requireContext().showToast(text)
}