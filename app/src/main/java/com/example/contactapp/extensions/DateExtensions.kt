package com.example.contactapp.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val FORMAT_DATE = "dd/MM/yyyy"

const val ID_CONTACT = "id_contato"

fun Date.formatDateToString(): String {
    return SimpleDateFormat(
        FORMAT_DATE,
        Locale.getDefault()
    ).format(this)
}
