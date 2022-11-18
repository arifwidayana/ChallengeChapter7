package com.arifwidayana.challengechapter7.presentation.ui.splashscreen

import com.arifwidayana.challengechapter7.base.model.Resource
import kotlinx.coroutines.flow.StateFlow

interface SplashScreenContract {
    val getBoardingResult: StateFlow<Resource<Boolean>>
    val getUsernameResult: StateFlow<Resource<String>>
    fun getBoarding()
    fun getUsername()
}