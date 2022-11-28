package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSource
import com.arifwidayana.challengechapter7.data.local.model.entity.FavoriteEntity
import com.arifwidayana.challengechapter7.data.local.model.request.FavoriteRequest
import com.arifwidayana.challengechapter7.data.network.datasource.MovieDataSource
import com.arifwidayana.challengechapter7.data.network.model.response.movie.details.DetailMovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface DetailMovieRepository {
    suspend fun getMovieDetail(idMovie: Int): Flow<Resource<DetailMovieResponse>>
    suspend fun getUsername(): Flow<Resource<String>>
    suspend fun insertFavoriteMovie(favoriteRequest: FavoriteRequest): Flow<Resource<Unit>>
    suspend fun getFavoriteById(idMovie: Int): Flow<Resource<FavoriteEntity>>
    suspend fun deleteFavoriteById(idMovie: Int): Flow<Resource<Unit>>
}

class DetailMovieRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource,
    private val localDataSource: LocalDataSource,
    private val movieDataSource: MovieDataSource
): DetailMovieRepository, BaseRepository() {
    override suspend fun getMovieDetail(idMovie: Int): Flow<Resource<DetailMovieResponse>> = flow {
        emit(proceed { movieDataSource.getMoviesDetail(idMovie) })
    }

    override suspend fun getUsername(): Flow<Resource<String>> = flow {
        emit(proceed { userPreferenceDataSource.getUsername().first() })
    }

    override suspend fun insertFavoriteMovie(favoriteRequest: FavoriteRequest): Flow<Resource<Unit>> = flow {
        emit(proceed { localDataSource.insertMovie(favoriteRequest) })
    }

    override suspend fun getFavoriteById(idMovie: Int): Flow<Resource<FavoriteEntity>> = flow {
        emit(proceed { localDataSource.getFavoriteById(idMovie).first() })
    }

    override suspend fun deleteFavoriteById(idMovie: Int): Flow<Resource<Unit>> = flow {
        emit(proceed { localDataSource.deleteFavorite(idMovie) })
    }
}