package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.local.model.request.EditProfileRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface EditProfileRepository {
    suspend fun getUsername(): Flow<Resource<String>>
    suspend fun getUser(username: String): Flow<Resource<UserEntity>>
    suspend fun updateProfileUser(username: String, editProfileRequest: EditProfileRequest): Flow<Resource<Unit>>
}

class EditProfileRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val localDataSource: LocalDataSource
): EditProfileRepository, BaseRepository() {
    override suspend fun getUsername(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDataSource.getUsername().first() })
    }

    override suspend fun getUser(username: String): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.getUser(username).first() })
    }

    override suspend fun updateProfileUser(username: String, editProfileRequest: EditProfileRequest): Flow<Resource<Unit>> = flow {
        emit(proceed { localDataSource.updateProfileUser(username, editProfileRequest) })
    }
}