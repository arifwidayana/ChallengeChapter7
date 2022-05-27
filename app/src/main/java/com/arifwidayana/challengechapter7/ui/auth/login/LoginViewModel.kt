package com.arifwidayana.challengechapter7.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.arifwidayana.challengechapter7.base.arch.BaseViewModellmpl
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.request.AuthRequest
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): BaseViewModellmpl(), LoginContract.ViewModel {
    private val syncUserLiveData = MutableLiveData<Resource<UserEntity>>()

    override fun getLoginUserLiveData(): LiveData<Resource<UserEntity>> = syncUserLiveData

    override fun loginUser(username: String, password: String) {
        syncUserLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responseDataUser = loginRepository.postLoginUser(AuthRequest(username, password))
                viewModelScope.launch(Dispatchers.Main) {
                    syncUserLiveData.value = Resource.Success(responseDataUser)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    syncUserLiveData.value = Resource.Error(null, e.message.orEmpty())
                }
            }
        }
    }

}