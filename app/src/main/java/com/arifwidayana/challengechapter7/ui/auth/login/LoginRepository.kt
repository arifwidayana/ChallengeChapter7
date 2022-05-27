package com.arifwidayana.challengechapter7.ui.auth.login

import com.arifwidayana.challengechapter7.base.arch.BaseRepositorylmpl
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.model.request.AuthRequest
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val localDataSource: LocalDataSource
): BaseRepositorylmpl(), LoginContract.Repository {
    override suspend fun postLoginUser(authRequest: AuthRequest): UserEntity {
        return localDataSource.loginUser(authRequest.username, authRequest.password)
    }

}