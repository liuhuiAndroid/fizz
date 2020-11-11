package com.sec.common.ktx

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.sec.common.R

/**
 * 设置Toolbar
 */
fun AppCompatActivity.toolbar(title: CharSequence? = null, showHomeAsUp: Boolean = true) {
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    toolbar?.let {
        setSupportActionBar(toolbar)
    }
    supportActionBar?.let {
        it.setDisplayHomeAsUpEnabled(showHomeAsUp)
        it.setHomeButtonEnabled(true)
        it.title = title
    }
}

/**
 * 设置Toolbar
 */
fun AppCompatActivity.toolbar(@StringRes resId: Int, showHomeAsUp: Boolean = true) {
    toolbar(getString(resId), showHomeAsUp)
}

/**
 * 更新Toolbar标题
 */
fun AppCompatActivity.toolbarTitle(title: CharSequence?) {
    supportActionBar?.title = title
}