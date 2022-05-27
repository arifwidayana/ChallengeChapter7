package com.arifwidayana.challengechapter7.ui.auth.login

import androidx.lifecycle.LiveData
import com.arifwidayana.challengechapter7.base.arch.BaseContract
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.request.AuthRequest
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity

interface LoginContract {
    interface View: BaseContract.BaseView {
        fun onClick()
        fun checkFormValidation(): Boolean
    }

    interface ViewModel: BaseContract.BaseViewModel {
        fun getLoginUserLiveData(): LiveData<Resource<UserEntity>>
        fun loginUser(username: String, password: String)
    }

    interface Repository: BaseContract.BaseRepository {
        suspend fun postLoginUser(authRequest: AuthRequest): UserEntity
    }
}