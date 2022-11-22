package com.arifwidayana.challengechapter7.presentation.ui.homepage.home

import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.network.model.response.movie.MovieResponse
import kotlinx.coroutines.flow.StateFlow

interface HomeContract {
    val getUserResult: StateFlow<Resource<UserEntity>>
    val getNowPlayingResult: StateFlow<Resource<MovieResponse>>
    val getPopularResult: StateFlow<Resource<MovieResponse>>
    val getUpComingResult: StateFlow<Resource<MovieResponse>>
    val getTopRatedResult: StateFlow<Resource<MovieResponse>>
    fun getUser()
    fun getMovieListNowPlaying()
    fun getMovieListPopular()
    fun getMovieListUpComing()
    fun getMovieListTopRated()
}