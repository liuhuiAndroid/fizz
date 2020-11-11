package com.sec.common.ktx

import java.math.BigDecimal

/**
 * double四舍五入保留两位小数
 */
inline fun Double.numberFormat(): String {
    val temp = BigDecimal(this.toString()).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
    return if (temp - temp.toInt() == 0.toDouble()) {
        temp.toInt().toString()
    } else temp.toString()
}