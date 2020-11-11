package com.sec.common.ktx

import android.view.View
import com.tencent.mmkv.MMKV

const val DEBOUNCE_CLICK = "DEBOUNCE_CLICK"

/**
 * View 防抖点击
 *
 * @param minTime 2次点击间隔最小时间，默认1000毫秒
 * @param click 点击回调
 */
inline fun View.debounceClick(
    minTime: Long = 500L,
    crossinline click: (v: View) -> Unit = {}
) {
    setOnClickListener {
        val currentClickTime = System.currentTimeMillis()
        val latestClickTime = MMKV.defaultMMKV().decodeLong(DEBOUNCE_CLICK, 0L)
        if (currentClickTime - latestClickTime > minTime) {
            MMKV.defaultMMKV().encode(DEBOUNCE_CLICK, currentClickTime)
            click.invoke(it)
        }
    }
}