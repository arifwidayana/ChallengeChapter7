package com.arifwidayana.challengechapter7.presentation.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.request.LoginRequest
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): LoginContract, ViewModel() {
    private val _loginUserResult = MutableStateFlow<Resource<UserEntity>>(Resource.Empty())
    override val loginUserResult: StateFlow<Resource<UserEntity>> = _loginUserResult

    override fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            loginRepository.loginUser(loginRequest).collect {
                if (it.data?.username == loginRequest.username && it.data?.password == loginRequest.password) {
                    loginRepository.setUsername(loginRequest.username.toString()).first()
                    _loginUserResult.value = Resource.Success(it.data)
                } else {
                    _loginUserResult.value = Resource.Error(message = "Username and password is wrong")
                }
            }
        }
    }
}