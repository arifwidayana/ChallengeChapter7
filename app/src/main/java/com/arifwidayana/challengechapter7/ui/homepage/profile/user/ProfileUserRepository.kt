package com.arifwidayana.challengechapter7.ui.homepage.profile.user

import com.arifwidayana.challengechapter7.base.arch.BaseRepositorylmpl
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import javax.inject.Inject

class ProfileUserRepository @Inject constructor(
    private val localDataSource: LocalDataSource
): BaseRepositorylmpl(), ProfileUserContract.Repostiory {
    override suspend fun getUser(username: String): UserEntity {
        return localDataSource.getUserData(username)
    }

    override suspend fun postUpdateUser(userEntity: UserEntity) {
        return localDataSource.updateUser(userEntity)
    }
}