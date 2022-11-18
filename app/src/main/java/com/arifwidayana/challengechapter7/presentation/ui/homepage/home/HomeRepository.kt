package com.arifwidayana.challengechapter7.presentation.ui.homepage.home

import com.arifwidayana.challengechapter7.base.arch.BaseRepositorylmpl
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.network.datasource.MovieDataSource
import com.arifwidayana.challengechapter7.data.network.model.response.movie.ListPlayingMovie
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val movieDataSource: MovieDataSource,
    private val localDataSource: LocalDataSource
): BaseRepositorylmpl(), HomeContract.Repository {

    override suspend fun getUser(username: String): UserEntity {
        return localDataSource.getUserData(username)
    }

    override suspend fun getMovieListNowPlaying(): ListPlayingMovie {
        return movieDataSource.getMoviesNowPlaying()
    }

    override suspend fun getMovieListPopular(): ListPlayingMovie {
        return movieDataSource.getMoviesPopular()
    }

    override suspend fun getMovieListUpComing(): ListPlayingMovie {
        return movieDataSource.getMoviesUpComing()
    }

    override suspend fun getMovieListTopRated(): ListPlayingMovie {
        return movieDataSource.getMoviesTopRated()
    }

}