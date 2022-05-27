package com.arifwidayana.challengechapter7.ui.homepage.profile.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arifwidayana.challengechapter7.base.arch.BaseViewModellmpl
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileUserViewModel @Inject constructor(
    private val profileUserRepository: ProfileUserRepository
): BaseViewModellmpl(), ProfileUserContract.ViewModel {
    private val getUserData = MutableLiveData<UserEntity>()

    override fun getUserResult(): LiveData<UserEntity> = getUserData

    override fun getUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseDataUser = profileUserRepository.getUser(username)
            viewModelScope.launch(Dispatchers.Main) {
                getUserData.value = responseDataUser
            }
        }
    }

    override fun updateUser(userEntity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            profileUserRepository.postUpdateUser(userEntity)
        }
    }
}