package com.arifwidayana.challengechapter7.data.local.model.request

data class EditProfileRequest(
    val name: String,
    val email: String,
    val age: Int,
    val phoneNumber: String
)