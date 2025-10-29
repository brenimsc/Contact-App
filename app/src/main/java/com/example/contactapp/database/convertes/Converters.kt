package com.example.contactapp.database.convertes

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun dateToLong(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun longToDate(long: Long?): Date? {
        return long?.let { Date(it) }
    }
}