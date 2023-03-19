package com.example.kazdreamweather.android.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String.formatToDay(): String {

    val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val date = LocalDateTime.parse(this, firstApiFormat)
    val result = buildString {
        this.append(date.dayOfMonth.toString())
        this.append(" ")
        this.append(date.month)
    }
    return result
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.formatToHours(): String {

    val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val date = LocalDateTime.parse(this, firstApiFormat)
    val result = buildString {
        this.append(date.hour)
        this.append(":")
        this.append(date.minute)
        this.append("0")
    }
    return result
}