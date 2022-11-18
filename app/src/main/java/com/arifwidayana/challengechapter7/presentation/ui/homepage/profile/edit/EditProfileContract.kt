package com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.edit

import androidx.lifecycle.LiveData
import com.arifwidayana.challengechapter7.base.arch.BaseContract
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity

interface EditProfileContract {
    interface View: BaseContract.BaseView {
        fun getData()
        fun checkFormValidation(): Boolean
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getUserResult(): LiveData<UserEntity>
        fun getUser(username: String)
        fun updateUser(userEntity: UserEntity)
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun getUser(username: String): UserEntity
        suspend fun postUpdateUser(userEntity: UserEntity)
    }
}