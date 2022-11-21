package com.arifwidayana.challengechapter7.presentation.ui.auth.login

import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.request.LoginRequest
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.StateFlow

interface LoginContract {
    val loginUserResult: StateFlow<Resource<UserEntity>>
    fun loginUser(loginRequest: LoginRequest)
}