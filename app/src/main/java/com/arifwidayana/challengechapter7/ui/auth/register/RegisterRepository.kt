package com.arifwidayana.challengechapter7.ui.auth.register

import com.arifwidayana.challengechapter7.base.arch.BaseRepositorylmpl
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val localDataSource: LocalDataSource
): BaseRepositorylmpl(), RegisterContract.Repository {
    override suspend fun checkStatusUser(username: String) {
        localDataSource.checkUser(username)
    }

    override suspend fun postRegisterUser(userEntity: UserEntity) {
        localDataSource.registerUser(userEntity)
    }
}