package com.arifwidayana.challengechapter7.presentation.ui.auth.register

import androidx.lifecycle.LiveData
import com.arifwidayana.challengechapter7.base.arch.BaseContract
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity

interface RegisterContract {
    interface View: BaseContract.BaseView {
        fun checkFormValidation(): Boolean
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getRegisterUserLiveData(): LiveData<Resource<Unit>>
        fun registerUser(userEntity: UserEntity)
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun checkStatusUser(username: String)
        suspend fun postRegisterUser(userEntity: UserEntity)
    }
}