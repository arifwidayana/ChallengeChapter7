package com.arifwidayana.challengechapter7.ui.homepage.home

import com.arifwidayana.challengechapter7.base.arch.BaseRepositorylmpl
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import com.arifwidayana.challengechapter7.data.network.datasource.MoviesDataSource
import com.arifwidayana.challengechapter7.data.network.model.response.listmovies.ListPlayingMovie
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val moviesDataSource: MoviesDataSource,
    private val localDataSource: LocalDataSource
): BaseRepositorylmpl(), HomeContract.Repository {

    override suspend fun getUser(username: String): UserEntity {
        return localDataSource.getUserData(username)
    }

    override suspend fun getMovieListNowPlaying(): ListPlayingMovie {
        return moviesDataSource.getMoviesNowPlaying()
    }

    override suspend fun getMovieListPopular(): ListPlayingMovie {
        return moviesDataSource.getMoviesPopular()
    }

    override suspend fun getMovieListUpComing(): ListPlayingMovie {
        return moviesDataSource.getMoviesUpComing()
    }

    override suspend fun getMovieListTopRated(): ListPlayingMovie {
        return moviesDataSource.getMoviesTopRated()
    }

}