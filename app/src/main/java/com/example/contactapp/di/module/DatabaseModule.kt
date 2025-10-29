package com.example.contactapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.contactapp.database.ContactAppDatabase
import com.example.contactapp.database.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providerDatabase(@ApplicationContext context: Context): ContactAppDatabase =
        Room.databaseBuilder(
            context,
            ContactAppDatabase::class.java,
            "contactApp.db"
        ).build()

    @Provides
    fun providerContactDao(db: ContactAppDatabase): ContactDao {
        return db.contactDao()
    }


}