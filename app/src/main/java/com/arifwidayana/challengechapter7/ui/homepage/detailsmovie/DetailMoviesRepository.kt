package com.arifwidayana.challengechapter7.ui.homepage.detailsmovie

import com.arifwidayana.challengechapter7.base.arch.BaseRepositorylmpl
import com.arifwidayana.challengechapter7.data.network.datasource.MoviesDataSource
import com.arifwidayana.challengechapter7.data.network.model.response.details.DetailsMovie
import javax.inject.Inject

class DetailMoviesRepository @Inject constructor(
    private val moviesDataSource: MoviesDataSource
): BaseRepositorylmpl(), DetailMoviesContract.Repository {

    override suspend fun getMovieDetail(id: Int): DetailsMovie {
        return moviesDataSource.getMoviesDetail(id)
    }
}