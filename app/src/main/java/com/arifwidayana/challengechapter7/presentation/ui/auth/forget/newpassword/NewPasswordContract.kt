package com.arifwidayana.challengechapter7.presentation.ui.auth.forget.newpassword

import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.request.NewPasswordRequest
import kotlinx.coroutines.flow.StateFlow

interface NewPasswordContract {
    val updateUserResult: StateFlow<Resource<Unit>>
    fun updateUser(newPasswordRequest: NewPasswordRequest)
}