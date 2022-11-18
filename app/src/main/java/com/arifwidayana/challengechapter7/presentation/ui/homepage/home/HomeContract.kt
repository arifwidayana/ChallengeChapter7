package com.arifwidayana.challengechapter7.presentation.ui.homepage.home

import androidx.lifecycle.LiveData
import com.arifwidayana.challengechapter7.base.arch.BaseContract
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.network.model.response.movie.ListPlayingMovie

interface HomeContract {
    interface View: BaseContract.BaseView {
        fun getData()
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getUserResult(): LiveData<UserEntity>
        fun getNowPlayingResultLiveData(): LiveData<Resource<ListPlayingMovie>>
        fun getPopularResultLiveData(): LiveData<Resource<ListPlayingMovie>>
        fun getUpComingResultLiveData(): LiveData<Resource<ListPlayingMovie>>
        fun getTopRatedResultLiveData(): LiveData<Resource<ListPlayingMovie>>
        fun getUser(username: String)
        fun getMovieListNowPlaying()
        fun getMovieListPopular()
        fun getMovieListUpComing()
        fun getMovieListTopRated()
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun getUser(username: String): UserEntity
        suspend fun getMovieListNowPlaying(): ListPlayingMovie
        suspend fun getMovieListPopular(): ListPlayingMovie
        suspend fun getMovieListUpComing(): ListPlayingMovie
        suspend fun getMovieListTopRated(): ListPlayingMovie
    }
}