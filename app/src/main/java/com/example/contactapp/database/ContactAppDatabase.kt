package com.example.contactapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.contactapp.data.Contact
import com.example.contactapp.database.convertes.Converters

@Database(entities = [Contact::class], version = 1)
@TypeConverters(Converters::class)
abstract class ContactAppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao
}