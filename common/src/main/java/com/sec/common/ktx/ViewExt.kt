package com.sec.common.ktx

import android.view.View

/**
 * 设置View可见,View.visibility = View.VISIBLE
 */
fun View.visibleOrGone(flag: Boolean) = run {
    visibility = if (flag) View.VISIBLE else View.GONE
}

/**
 * 设置View可见,View.visibility = View.VISIBLE
 */
inline fun View.visible() = run {
    visibility = View.VISIBLE
}

/**
 * 设置View不可见,View.visibility = View.INVISIBLE
 */
inline fun View.invisible() = run {
    visibility = View.INVISIBLE
}

/**
 * 设置View消失,View.visibility = View.GONE
 */
inline fun View.gone() = run {
    visibility = View.GONE
}