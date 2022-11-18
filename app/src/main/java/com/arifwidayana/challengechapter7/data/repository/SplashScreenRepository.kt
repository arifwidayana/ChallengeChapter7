package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SplashScreenRepository {
    suspend fun getBoarding(): Flow<Resource<Boolean>>
    suspend fun getUsername(): Flow<Resource<String>>
}

class SplashScreenRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
): SplashScreenRepository, BaseRepository() {
    override suspend fun getBoarding(): Flow<Resource<Boolean>> = flow {
        emit(proceed { userPreferenceDataSource.getBoarding().first() })
    }

    override suspend fun getUsername(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDataSource.getUsername().first() })
    }
}