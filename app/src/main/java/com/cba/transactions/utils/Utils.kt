package com.cba.transactions.utils

import android.annotation.SuppressLint
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class Utils {
    companion object{
        @SuppressLint("SimpleDateFormat")
        fun convertDate(dateTime:String): String {
            try {
                val input = SimpleDateFormat("yyyy-MM-dd")
                val output = SimpleDateFormat("E dd MMM")
                val getAbbreviate = input.parse(dateTime)    // parse input
                return output.format(getAbbreviate)    // format output
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return ""

        }
        fun covertTimeToText(dataDate: String): String {
            var convTime: String = ""
            val prefix = ""
            val suffix = "ago"
            try {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                val pasTime: Date = dateFormat.parse(dataDate)
                val nowTime = Date()
                val dateDiff: Long = nowTime.time - pasTime.time
                val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
                val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
                val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
                val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)
                if (second < 60) {
                    convTime = "$second Seconds $suffix"
                } else if (minute < 60) {
                    convTime = "$minute Minutes $suffix"
                } else if (hour < 24) {
                    convTime = "$hour Hours $suffix"
                } else if (day >= 7) {
                    convTime = when {
                        day > 360 -> {
                            (day / 360).toString() + " Year " + suffix
                        }
                        day > 30 -> {
                            (day / 30).toString() + " Months " + suffix
                        }
                        else -> {
                            (day / 7).toString() + " Week " + suffix
                        }
                    }
                } else if (day < 7) {
                    convTime = "$day Days $suffix"
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                e.message?.let { Log.e("ConvTimeE", it) }
            }
            return convTime
        }

    }
}