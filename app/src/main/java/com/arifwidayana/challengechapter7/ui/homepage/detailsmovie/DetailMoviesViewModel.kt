package com.arifwidayana.challengechapter7.ui.homepage.detailsmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arifwidayana.challengechapter7.base.arch.BaseViewModellmpl
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.network.model.response.details.DetailsMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMoviesViewModel @Inject constructor(
    private val detailMoviesRepository: DetailMoviesRepository
): BaseViewModellmpl(), DetailMoviesContract.ViewModel {
    private var detailMoviesLiveData = MutableLiveData<Resource<DetailsMovie>>()
    private var movieId = MutableLiveData<Int?>()

    override fun getNowPlayingDetailResponse(): LiveData<Resource<DetailsMovie>> {
        return detailMoviesLiveData
    }

    override fun getMovieId(): LiveData<Int?> {
        return movieId
    }

    override fun setIntentData(id: Int) {
        movieId.value = id
    }

    override fun getMovieDetail(id: Int) {
        detailMoviesLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = detailMoviesRepository.getMovieDetail(id)
                viewModelScope.launch(Dispatchers.Main) {
                    detailMoviesLiveData.value = Resource.Success(response)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    detailMoviesLiveData.value = Resource.Error(null, e.message.orEmpty())
                }
            }
        }
    }
}