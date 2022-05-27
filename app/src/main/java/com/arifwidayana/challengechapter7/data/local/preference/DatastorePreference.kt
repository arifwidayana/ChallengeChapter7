package com.arifwidayana.challengechapter7.data.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arifwidayana.challengechapter7.data.local.model.response.UserPreference
import com.arifwidayana.challengechapter7.utils.Constant
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatastorePreference @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val USERNAME = stringPreferencesKey(Constant.USERNAME)
        private val LOGIN = booleanPreferencesKey(Constant.LOGIN)
        private val ON_BOARDING = booleanPreferencesKey(Constant.FINISHED)
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constant.PREF)
    }

    fun getUserSession(): Flow<UserPreference> = context.dataStore.data.map { pref ->
        UserPreference(
            pref[USERNAME]?: "",
            pref[LOGIN]?: false,
            pref[ON_BOARDING]?: false
        )
    }

    suspend fun saveUserSession(userPreference: UserPreference){
        context.dataStore.edit { pref ->
            pref[USERNAME] = userPreference.username
            pref[LOGIN] = userPreference.loginSession
        }
    }

    suspend fun saveOnBoardSession(userPreference: UserPreference) {
        context.dataStore.edit {
            it[ON_BOARDING] = userPreference.onBoardSession
        }
    }

    suspend fun deleteUserSession() {
        context.dataStore.edit {
            it.clear()
        }
    }
}