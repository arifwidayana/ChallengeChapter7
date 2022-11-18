package com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.user

import androidx.lifecycle.LiveData
import com.arifwidayana.challengechapter7.base.arch.BaseContract
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity

interface ProfileUserContract {
    interface View: BaseContract.BaseView {
        fun getData()
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getUserResult(): LiveData<UserEntity>
        fun getUser(username: String)
        fun updateUser(userEntity: UserEntity)
    }

    interface Repostiory: BaseContract.BaseRepository {
        suspend fun getUser(username: String): UserEntity
        suspend fun postUpdateUser(userEntity: UserEntity)
    }
}