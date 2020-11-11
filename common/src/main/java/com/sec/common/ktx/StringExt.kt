package com.sec.common.ktx

import java.lang.NumberFormatException
import java.security.MessageDigest

private val HEX_DIGITS = charArrayOf(
    '0', '1', '2', '3', '4',
    '5', '6', '7', '8', '9',
    'a', 'b', 'c', 'd', 'e', 'f'
)

// 分隔符
private val SEPARATOR_ARR = charArrayOf(',', '、', ' ')

/**
 * 计算MD5
 */
fun md5(vararg inputs: String): String {
    val content = inputs.joinToString("")
    try {
        val btInput = content.toByteArray()
        val mdInst = MessageDigest.getInstance("MD5")
        mdInst.update(btInput)
        val md = mdInst.digest()
        val str = CharArray(md.size * 2)
        var k = 0
        var i = 0
        do {
            val byte0 = md[i]
            str[k++] = HEX_DIGITS[byte0.toInt().ushr(4) and 0xf]
            str[k++] = HEX_DIGITS[byte0.toInt() and 0xf]
            i++
        } while (i < md.size)
        return String(str)
    } catch (e: Exception) {
        return ""
    }
}

/**
 * 分隔字符串
 */
fun splitField(value: String?): List<String> {
    val valueTri = value?.trim()
    return if (valueTri.isNullOrEmpty()) {
        listOf()
    } else {
        val separatorChar = SEPARATOR_ARR.find { valueTri.indexOf(it) != -1 }
        if (separatorChar == null) {
            listOf(valueTri)
        } else {
            valueTri.split(separatorChar)
        }
    }
}

fun String?.parseInt(defValue: Int = 0): Int {
    return try {
        Integer.parseInt(this.toString())
    } catch (e: NumberFormatException) {
        defValue
    }
}

/**
 * 字符串不为空
 */
fun String?.valueNotNullFormat() = if (this.isNullOrEmpty()) "" else this

fun String?.parseFloat(defValue: Float = 0f): Float = try {
    this?.toFloat() ?: defValue
} catch (e: NumberFormatException) {
    defValue
}



