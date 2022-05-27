package com.arifwidayana.challengechapter7.data.network.datasource

import com.arifwidayana.challengechapter7.data.network.model.response.details.DetailsMovie
import com.arifwidayana.challengechapter7.data.network.model.response.listmovies.ListPlayingMovie
import com.arifwidayana.challengechapter7.data.network.service.MovieService
import javax.inject.Inject

class MoviesDataSourcelmpl @Inject constructor(
    private val movieService: MovieService
): MoviesDataSource {
    override suspend fun getMoviesNowPlaying(): ListPlayingMovie {
        return movieService.getNowPlayingMovie()
    }

    override suspend fun getMoviesPopular(): ListPlayingMovie {
        return movieService.getPopularMovie()
    }

    override suspend fun getMoviesUpComing(): ListPlayingMovie {
        return movieService.getUpComingMovie()
    }

    override suspend fun getMoviesTopRated(): ListPlayingMovie {
        return movieService.getTopRatedMovie()
    }

    override suspend fun getMoviesDetail(movieId: Int): DetailsMovie {
        return movieService.getDetailMovie(movieId)
    }
}