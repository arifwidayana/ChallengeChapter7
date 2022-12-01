package com.arifwidayana.challengechapter7.presentation.ui.auth.forget

import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.request.ForgetPasswordRequest
import kotlinx.coroutines.flow.StateFlow

interface ForgetPasswordContract {
    val findUserResult: StateFlow<Resource<ForgetPasswordRequest>>
    fun findUser(forgetPasswordRequest: ForgetPasswordRequest)
}