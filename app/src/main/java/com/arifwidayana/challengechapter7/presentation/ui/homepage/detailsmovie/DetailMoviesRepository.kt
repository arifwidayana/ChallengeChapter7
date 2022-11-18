package com.arifwidayana.challengechapter7.presentation.ui.homepage.detailsmovie

import com.arifwidayana.challengechapter7.base.arch.BaseRepositorylmpl
import com.arifwidayana.challengechapter7.data.network.datasource.MovieDataSource
import javax.inject.Inject

class DetailMoviesRepository @Inject constructor(
    private val movieDataSource: MovieDataSource
): BaseRepositorylmpl(), DetailMoviesContract.Repository {

    override suspend fun getMovieDetail(id: Int): DetailsMovie {
        return movieDataSource.getMoviesDetail(id)
    }
}