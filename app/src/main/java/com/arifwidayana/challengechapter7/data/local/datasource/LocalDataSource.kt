package com.arifwidayana.challengechapter7.data.local.datasource

import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import com.arifwidayana.challengechapter7.data.local.model.response.UserPreference
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun registerUser(userEntity: UserEntity)
    suspend fun loginUser(username: String?, password: String?): UserEntity
    suspend fun checkUser(username: String): Int
    suspend fun getUserData(username: String): UserEntity
    suspend fun updateUser(userEntity: UserEntity)
    suspend fun getUserSession(): Flow<UserPreference>
    suspend fun saveUserSession(userPreference: UserPreference)
    suspend fun clearSession()
}