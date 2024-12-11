package com.aatif.jetpack_compose_starter_template.data.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class TokenManager(private val context: Context) {

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    fun getAccessToken(): String? {
        return runBlocking {
            context.dataStore.data.first()[ACCESS_TOKEN_KEY]
        }
    }

    fun getRefreshToken(): String? {
        return runBlocking {
            context.dataStore.data.first()[REFRESH_TOKEN_KEY]
        }
    }

    fun saveAccessToken(token: String) {
        runBlocking {
            context.dataStore.edit { preferences ->
                preferences[ACCESS_TOKEN_KEY] = token
            }
        }
    }

    fun saveRefreshToken(token: String) {
        runBlocking {
            context.dataStore.edit { preferences ->
                preferences[REFRESH_TOKEN_KEY] = token
            }
        }
    }
}
