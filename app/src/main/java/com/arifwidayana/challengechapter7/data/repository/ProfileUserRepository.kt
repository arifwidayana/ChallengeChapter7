package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.local.model.request.ProfileUserRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ProfileUserRepository {
    suspend fun getUsername(): Flow<Resource<String>>
    suspend fun getUser(username: String): Flow<Resource<UserEntity>>
    suspend fun updateImageProfile(profileUserRequest: ProfileUserRequest): Flow<Resource<Unit>>
    suspend fun logoutUser(): Flow<Resource<Unit>>
}

class ProfileUserRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val localDataSource: LocalDataSource
): ProfileUserRepository, BaseRepository() {
    override suspend fun getUsername(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDataSource.getUsername().first() })
    }

    override suspend fun getUser(username: String): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.getUser(username).first() })
    }

    override suspend fun updateImageProfile(profileUserRequest: ProfileUserRequest): Flow<Resource<Unit>> = flow {
        emit(proceed { localDataSource.updateImageProfile(profileUserRequest) })
    }

    override suspend fun logoutUser(): Flow<Resource<Unit>> = flow {
        emit(proceed { userPreferenceDataSource.logoutUser() })
    }
}