package com.arifwidayana.challengechapter7.presentation.ui.homepage.detailmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.network.model.response.movie.details.DetailMovieResponse
import com.arifwidayana.challengechapter7.data.repository.DetailMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val detailMovieRepository: DetailMovieRepository
): DetailMovieContract, ViewModel() {
    private val _getMovieDetailResult = MutableStateFlow<Resource<DetailMovieResponse>>(Resource.Empty())
    override val getMovieDetailResult: StateFlow<Resource<DetailMovieResponse>> = _getMovieDetailResult

    override fun getMovieDetail(idMovie: Int) {
        _getMovieDetailResult.value = Resource.Loading()
        viewModelScope.launch {
            detailMovieRepository.getMovieDetail(idMovie).collect {
                _getMovieDetailResult.value = Resource.Success(it.data)
            }
        }
    }
}