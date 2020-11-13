package com.sec.common.ktx

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * 当天内  显示 小时 分钟
 * 昨天内  显示 "昨天"+小时 分钟
 * 前天内  显示 "前天"+小时 分钟
 * 其他显示 年月日+小时 分钟
 */
fun Long.timeRule(): String {
    if (DateUtils.isToday(this)) {
        return SimpleDateFormat("HH:mm", Locale.CHINA).format(this)
    } else {
        val calendar = GregorianCalendar()
        calendar.timeInMillis = this
        //显示时间
        val thenYear = calendar.get(Calendar.YEAR)
        val thenMonth = calendar.get(Calendar.MONTH)
        val thenMonthDay = calendar.get(Calendar.DATE)
        //当前时间
        calendar.timeInMillis = System.currentTimeMillis()
        return when {
            thenYear != calendar.get(Calendar.YEAR) ->
                SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(this)
            thenMonth == calendar[Calendar.MONTH] && thenMonthDay == calendar[Calendar.DATE] - 1 ->
                "昨天 ${SimpleDateFormat("HH:mm", Locale.CHINA).format(this)}"
            thenMonth == calendar[Calendar.MONTH] && thenMonthDay == calendar[Calendar.DATE] - 2 ->
                "前天 ${SimpleDateFormat("HH:mm", Locale.CHINA).format(this)}"
            else -> SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(this)
        }
    }
}