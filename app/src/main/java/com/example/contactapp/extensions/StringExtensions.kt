package com.example.contactapp.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val FORMAT_DATE_D_M_YYYY = "d/M/yyyy"

fun String.formatStringToDate(): Date? {
    return try {
        SimpleDateFormat(
            FORMAT_DATE_D_M_YYYY,
            Locale.getDefault()
        ).parse(this)
    } catch (e: ParseException) {
        null
    }
}