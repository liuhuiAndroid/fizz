package com.sec.common.ktx

import android.view.View

/**
 * 设置View可见,View.visibility = View.VISIBLE
 */
fun View.visibleOrGone(flag: Boolean) = run {
    visibility = if (flag) View.VISIBLE else View.GONE
}