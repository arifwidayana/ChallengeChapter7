package com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.user

import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.StateFlow

interface ProfileUserContract {
    val getUserResult: StateFlow<Resource<UserEntity>>
    val updateProfileImageResult: StateFlow<Resource<Unit>>
    val logoutUserResult: StateFlow<Resource<Unit>>
    fun getUser()
    fun updateProfileImage(imageProfile: String)
    fun logoutUser()
}