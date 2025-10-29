package com.example.contactapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String = "",
    val lastName: String = "",
    val phone: String = "",
    val photo: String = "",
    val birthday: Date? = null
)