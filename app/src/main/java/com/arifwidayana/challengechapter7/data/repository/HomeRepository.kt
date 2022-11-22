package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.network.datasource.MovieDataSource
import com.arifwidayana.challengechapter7.data.network.model.response.movie.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface HomeRepository {
    suspend fun getUsername(): Flow<Resource<String>>
    suspend fun getUser(username: String): Flow<Resource<UserEntity>>
    suspend fun getMovieListNowPlaying(): Flow<Resource<MovieResponse>>
    suspend fun getMovieListPopular(): Flow<Resource<MovieResponse>>
    suspend fun getMovieListUpComing(): Flow<Resource<MovieResponse>>
    suspend fun getMovieListTopRated(): Flow<Resource<MovieResponse>>
}

class HomeRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val localDataSource: LocalDataSource,
    private val movieDataSource: MovieDataSource
): HomeRepository, BaseRepository() {
    override suspend fun getUsername(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDataSource.getUsername().first() })
    }

    override suspend fun getUser(username: String): Flow<Resource<UserEntity>> = flow {
        emit(proceed { localDataSource.getUser(username).first() })
    }

    override suspend fun getMovieListNowPlaying(): Flow<Resource<MovieResponse>> = flow {
        emit(proceed { movieDataSource.getMoviesNowPlaying() })
    }

    override suspend fun getMovieListPopular(): Flow<Resource<MovieResponse>> = flow {
        emit(proceed { movieDataSource.getMoviesPopular() })
    }

    override suspend fun getMovieListUpComing(): Flow<Resource<MovieResponse>> = flow {
        emit(proceed { movieDataSource.getMoviesUpComing() })
    }

    override suspend fun getMovieListTopRated(): Flow<Resource<MovieResponse>> = flow {
        emit(proceed { movieDataSource.getMoviesTopRated() })
    }
}