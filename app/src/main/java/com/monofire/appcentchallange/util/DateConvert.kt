package com.monofire.appcentchallange.util

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DateConvert {
    fun getDate(milisecond: Long): String {
        val formatter = SimpleDateFormat("hh:mm")
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milisecond
        return formatter.format(calendar.time)
    }
}