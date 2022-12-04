package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.local.model.request.ForgetPasswordRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface ForgetPasswordRepository {
    suspend fun findUser(forgetPasswordRequest: ForgetPasswordRequest): Flow<Resource<UserEntity>>
}

class ForgetPasswordRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): ForgetPasswordRepository, BaseRepository() {
    override suspend fun findUser(forgetPasswordRequest: ForgetPasswordRequest): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.findUser(forgetPasswordRequest).first() })
    }
}