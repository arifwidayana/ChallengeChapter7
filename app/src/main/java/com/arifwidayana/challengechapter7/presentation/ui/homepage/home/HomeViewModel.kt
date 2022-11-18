package com.arifwidayana.challengechapter7.presentation.ui.homepage.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.network.model.response.movie.ListPlayingMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homepageRepository: HomeRepository
): BaseViewModellmpl(), HomeContract.ViewModel {
    private val movieListNowPlayingLiveData = MutableLiveData<Resource<ListPlayingMovie>>()
    private val movieListPopularLiveData = MutableLiveData<Resource<ListPlayingMovie>>()
    private val movieListUpcomingLiveData = MutableLiveData<Resource<ListPlayingMovie>>()
    private val movieListTopRatedLiveData = MutableLiveData<Resource<ListPlayingMovie>>()
    private val getUserData = MutableLiveData<UserEntity>()

    override fun getUserResult(): LiveData<UserEntity> = getUserData
    override fun getNowPlayingResultLiveData(): LiveData<Resource<ListPlayingMovie>> = movieListNowPlayingLiveData
    override fun getPopularResultLiveData(): LiveData<Resource<ListPlayingMovie>> = movieListPopularLiveData
    override fun getUpComingResultLiveData(): LiveData<Resource<ListPlayingMovie>> = movieListUpcomingLiveData
    override fun getTopRatedResultLiveData(): LiveData<Resource<ListPlayingMovie>> = movieListTopRatedLiveData

    override fun getUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseDataUser = homepageRepository.getUser(username)
            withContext(Dispatchers.Main) {
                getUserData.value = responseDataUser
            }
        }
    }

    override fun getMovieListNowPlaying() {
        movieListNowPlayingLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responseNowPlayingMovie = homepageRepository.getMovieListNowPlaying()

                viewModelScope.launch(Dispatchers.Main) {
                    movieListNowPlayingLiveData.value = Resource.Success(responseNowPlayingMovie)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    movieListNowPlayingLiveData.value = Resource.Error(null, e.message.orEmpty())
                }
            }
        }
    }

    override fun getMovieListPopular() {
        movieListPopularLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responsePopular = homepageRepository.getMovieListPopular()
                viewModelScope.launch(Dispatchers.Main) {
                    movieListPopularLiveData.value = Resource.Success(responsePopular)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    movieListPopularLiveData.value = Resource.Error(null, e.message.orEmpty())
                }
            }
        }
    }

    override fun getMovieListUpComing() {
        movieListUpcomingLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responseUpComing = homepageRepository.getMovieListUpComing()
                viewModelScope.launch(Dispatchers.Main) {
                    movieListUpcomingLiveData.value = Resource.Success(responseUpComing)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    movieListUpcomingLiveData.value = Resource.Error(null, e.message.orEmpty())
                }
            }
        }
    }

    override fun getMovieListTopRated() {
        movieListTopRatedLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responseTopRated = homepageRepository.getMovieListTopRated()
                viewModelScope.launch(Dispatchers.Main) {
                    movieListTopRatedLiveData.value = Resource.Success(responseTopRated)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    movieListTopRatedLiveData.value = Resource.Error(null, e.message.orEmpty())
                }
            }
        }
    }

}