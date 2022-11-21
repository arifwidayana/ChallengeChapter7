package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.local.model.request.RegisterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RegisterRepository {
    suspend fun registerUser(registerRequest: RegisterRequest): Flow<Resource<Unit>>
    suspend fun getUser(username: String): Flow<Resource<UserEntity>>
}

class RegisterRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): RegisterRepository, BaseRepository() {
    override suspend fun registerUser(registerRequest: RegisterRequest): Flow<Resource<Unit>> = flow {
        emit(proceed { localDataSource.registerUser(registerRequest) })
    }

    override suspend fun getUser(username: String): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.getUser(username).first() })
    }
}