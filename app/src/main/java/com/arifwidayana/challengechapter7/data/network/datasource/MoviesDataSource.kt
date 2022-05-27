package com.arifwidayana.challengechapter7.data.network.datasource

import com.arifwidayana.challengechapter7.data.network.model.response.details.DetailsMovie
import com.arifwidayana.challengechapter7.data.network.model.response.listmovies.ListPlayingMovie

interface MoviesDataSource {
    suspend fun getMoviesNowPlaying(): ListPlayingMovie
    suspend fun getMoviesPopular(): ListPlayingMovie
    suspend fun getMoviesUpComing(): ListPlayingMovie
    suspend fun getMoviesTopRated(): ListPlayingMovie
    suspend fun getMoviesDetail(movieId: Int): DetailsMovie
}