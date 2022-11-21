package com.arifwidayana.challengechapter7.presentation.ui.auth.register

import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.request.RegisterRequest
import kotlinx.coroutines.flow.StateFlow

interface RegisterContract {
    val registerUserResult: StateFlow<Resource<Unit>>
    fun registerUser(registerRequest: RegisterRequest)
}