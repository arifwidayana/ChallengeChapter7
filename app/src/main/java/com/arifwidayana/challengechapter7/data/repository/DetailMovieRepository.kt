package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.network.datasource.MovieDataSource
import com.arifwidayana.challengechapter7.data.network.model.response.movie.details.DetailMovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface DetailMovieRepository {
    suspend fun getMovieDetail(idMovie: Int): Flow<Resource<DetailMovieResponse>>
}

class DetailMovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource
): DetailMovieRepository, BaseRepository() {
    override suspend fun getMovieDetail(idMovie: Int): Flow<Resource<DetailMovieResponse>> = flow {
        emit(proceed { movieDataSource.getMoviesDetail(idMovie) })
    }
}