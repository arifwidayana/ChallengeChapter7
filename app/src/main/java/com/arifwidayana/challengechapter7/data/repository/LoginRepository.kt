package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.model.request.LoginRequest
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LoginRepository {
    suspend fun loginUser(loginRequest: LoginRequest): Flow<Resource<UserEntity>>
}

class LoginRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): LoginRepository, BaseRepository() {
    override suspend fun loginUser(loginRequest: LoginRequest): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.loginUser(loginRequest).first() })
    }
}