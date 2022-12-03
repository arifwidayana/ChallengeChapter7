package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.model.request.NewPasswordRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface NewPasswordRepository {
    suspend fun updatePassword(newPasswordRequest: NewPasswordRequest): Flow<Resource<Unit>>
}

class NewPasswordRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): NewPasswordRepository, BaseRepository() {
    override suspend fun updatePassword(newPasswordRequest: NewPasswordRequest): Flow<Resource<Unit>> = flow {
        emit(proceed { localDataSource.updatePassword(newPasswordRequest) })
    }
}