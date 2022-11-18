package com.arifwidayana.challengechapter7.presentation.ui.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
): BaseViewModellmpl(), RegisterContract.ViewModel {
    private val registerUserLiveData = MutableLiveData<Resource<Unit>>()

    override fun getRegisterUserLiveData(): LiveData<Resource<Unit>> = registerUserLiveData

    override fun registerUser(userEntity: UserEntity) {
        registerUserLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responseRegisterUser = registerRepository.postRegisterUser(userEntity)
                viewModelScope.launch(Dispatchers.Main) {
                    registerUserLiveData.value = Resource.Success(responseRegisterUser)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    registerUserLiveData.value = Resource.Error(null, e.message.orEmpty())
                }
            }
        }
    }
}