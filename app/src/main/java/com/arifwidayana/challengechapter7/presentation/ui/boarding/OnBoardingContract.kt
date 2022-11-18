package com.arifwidayana.challengechapter7.presentation.ui.boarding

import com.arifwidayana.challengechapter7.base.model.Resource
import kotlinx.coroutines.flow.StateFlow

interface OnBoardingContract {
    val setBoardingResult: StateFlow<Resource<Unit>>
    fun setBoarding()
}