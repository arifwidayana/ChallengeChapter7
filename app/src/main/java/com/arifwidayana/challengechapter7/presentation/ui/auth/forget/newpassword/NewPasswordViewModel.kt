package com.arifwidayana.challengechapter7.presentation.ui.auth.forget.newpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.request.NewPasswordRequest
import com.arifwidayana.challengechapter7.data.repository.NewPasswordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewPasswordViewModel @Inject constructor(
    private val newPasswordRepository: NewPasswordRepository
): NewPasswordContract, ViewModel() {
    private val _updateUserResult = MutableStateFlow<Resource<Unit>>(Resource.Empty())
    override val updateUserResult: StateFlow<Resource<Unit>> = _updateUserResult

    override fun updateUser(newPasswordRequest: NewPasswordRequest) {
        viewModelScope.launch {
            newPasswordRepository.updatePassword(newPasswordRequest).collect {
                _updateUserResult.value = Resource.Success(message = "Your password has been changed")
            }
        }
    }
}