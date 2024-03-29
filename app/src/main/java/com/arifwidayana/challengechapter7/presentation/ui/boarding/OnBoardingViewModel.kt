package com.arifwidayana.challengechapter7.presentation.ui.boarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.repository.OnBoardingRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository
): OnBoardingContract, ViewModel() {
    private val _setBoardingResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val setBoardingResult: StateFlow<Resource<Unit>> = _setBoardingResult

    override fun setBoarding() {
        viewModelScope.launch {
            onBoardingRepository.setBoarding(true).collect {
                _setBoardingResult.value = Resource.Success()
            }
        }
    }
}