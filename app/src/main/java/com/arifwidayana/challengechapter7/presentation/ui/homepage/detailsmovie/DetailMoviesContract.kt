package com.arifwidayana.challengechapter7.presentation.ui.homepage.detailsmovie

import androidx.lifecycle.LiveData
import com.arifwidayana.challengechapter7.base.arch.BaseContract
import com.arifwidayana.challengechapter7.base.model.Resource

interface DetailMoviesContract {
    interface View: BaseContract.BaseView {
        fun setContentData(data: DetailsMovie)
        fun getIntentData()
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getNowPlayingDetailResponse(): LiveData<Resource<DetailsMovie>>
        fun getMovieId(): LiveData<Int?>
        fun setIntentData(id: Int)
        fun getMovieDetail(id: Int)
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun getMovieDetail(id: Int): DetailsMovie
    }
}