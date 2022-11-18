package com.arifwidayana.challengechapter7.presentation.ui.auth.login

import com.arifwidayana.challengechapter7.base.arch.BaseRepositorylmpl
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.model.request.LoginRequest
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val localDataSource: LocalDataSource
): BaseRepositorylmpl(), LoginContract.Repository {
    override suspend fun postLoginUser(loginRequest: LoginRequest): UserEntity {
        return localDataSource.loginUser(loginRequest.username, loginRequest.password)
    }

}