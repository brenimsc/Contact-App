package com.example.contactapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.contactapp.data.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM Contact ORDER BY name")
    fun getAll(): Flow<List<Contact>>

    @Query("SELECT * FROM Contact WHERE id = :id")
    fun get(id: Long): Flow<Contact?>

    @Query("SELECT * FROM Contact WHERE name LIKE '%' || :name || '%' COLLATE NOCASE OR lastName LIKE '%' || :name || '%' COLLATE NOCASE ORDER BY name")
    fun getName(name: String): Flow<List<Contact>>

    @Query("DELETE FROM CONTACT WHERE id = :id")
    suspend fun delete(id: Long)
}