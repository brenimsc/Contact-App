package com.example.contactapp.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

const val LOGGED = "logado"
private const val USER = "user"
private const val PASSWORD = "password"

class LoginPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun getLoggedPreferences(logged: (Boolean?) -> Unit) {
        dataStore.data.collect { preferences ->
            logged.invoke(preferences[booleanPreferencesKey(LOGGED)])
        }
    }

    suspend fun setLoggedPreferences(logged: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(LOGGED)] = logged
        }
    }

    suspend fun getUserPreferences(user: (String?) -> Unit) {
        dataStore.data.collect { preferences ->
            user.invoke(preferences[stringPreferencesKey(USER)])
        }
    }

    suspend fun setUserPreferences(user: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(USER)] = user
        }
    }

    suspend fun getPasswordPreferences(password: (String?) -> Unit) {
        dataStore.data.collect { preferences ->
            password.invoke(preferences[stringPreferencesKey(PASSWORD)])
        }
    }

    suspend fun setPasswordPreferences(password: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(PASSWORD)] = password
        }
    }

    suspend fun getUserAndPassword(login: (user: String?, password: String?) -> Unit) {
        var user: String? = null
        var password: String? = null

        getUserPreferences {
            user = it

            CoroutineScope(IO).launch {
                getPasswordPreferences {
                    password = it

                    login.invoke(user, password)
                }
            }
        }
    }
}