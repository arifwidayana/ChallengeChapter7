package com.arifwidayana.challengechapter7.ui.homepage.profile.edit

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
class EditProfileViewModel @Inject constructor(
    private val editProfileRepository: EditProfileRepository
): BaseViewModellmpl(), EditProfileContract.ViewModel{
    private val getUserData = MutableLiveData<UserEntity>()

    override fun getUserResult(): LiveData<UserEntity> = getUserData

    override fun getUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseDataUser = editProfileRepository.getUser(username)
            try {
                viewModelScope.launch(Dispatchers.Main) {
                    getUserData.value = responseDataUser
                }
            } catch(e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
//                    Toast.makeText(, "", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun updateUser(userEntity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            editProfileRepository.postUpdateUser(userEntity)
        }
    }
}