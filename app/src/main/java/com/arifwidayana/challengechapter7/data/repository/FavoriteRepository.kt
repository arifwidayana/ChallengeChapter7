package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSource
import com.arifwidayana.challengechapter7.data.local.model.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface FavoriteRepository {
    suspend fun getUsername(): Flow<Resource<String>>
    suspend fun getFavorite(username: String): Flow<Resource<List<FavoriteEntity>>>
}

class FavoriteRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val localDataSource: LocalDataSource
): FavoriteRepository, BaseRepository() {
    override suspend fun getUsername(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDataSource.getUsername().first() })
    }

    override suspend fun getFavorite(username: String): Flow<Resource<List<FavoriteEntity>>> = flow {
        emit(proceed { localDataSource.getFavoriteMovie(username).first() })
    }
}