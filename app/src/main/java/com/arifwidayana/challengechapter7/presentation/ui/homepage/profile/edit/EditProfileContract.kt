package com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.edit

import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.local.model.request.EditProfileRequest
import kotlinx.coroutines.flow.StateFlow

interface EditProfileContract {
    val getUserResult: StateFlow<Resource<UserEntity>>
    val updateProfileUserResult: StateFlow<Resource<Unit>>
    fun getUser()
    fun updateProfileUser(editProfileRequest: EditProfileRequest)
}