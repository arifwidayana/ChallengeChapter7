package com.arifwidayana.challengechapter7.data.local.datasource

import com.arifwidayana.challengechapter7.data.local.model.response.UserDao
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import com.arifwidayana.challengechapter7.data.local.model.response.UserPreference
import com.arifwidayana.challengechapter7.data.local.preference.DatastorePreference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourcelmpl @Inject constructor(
    private val userDao: UserDao,
    private val datastorePreference: DatastorePreference
): LocalDataSource {
    override suspend fun registerUser(userEntity: UserEntity) {
        return userDao.insertUser(userEntity)
    }

    override suspend fun loginUser(username: String?, password: String?): UserEntity {
        return userDao.loginUser(username, password)
    }

    override suspend fun checkUser(username: String): Int {
        return userDao.checkUser(username)
    }

    override suspend fun getUserData(username: String): UserEntity {
        return userDao.getUser(username)
    }

    override suspend fun updateUser(userEntity: UserEntity) {
        return userDao.updateProfileUser(userEntity)
    }

    override suspend fun getUserSession(): Flow<UserPreference> {
        return datastorePreference.getUserSession()
    }

    override suspend fun saveUserSession(userPreference: UserPreference) {
        datastorePreference.saveUserSession(userPreference)
    }

    override suspend fun clearSession() {
        datastorePreference.deleteUserSession()
    }

}