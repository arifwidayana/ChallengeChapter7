package com.arifwidayana.challengechapter7.data.local.model.request

data class RegisterRequest(
    val name: String?,
    val imageProfile: String?,
    val email: String?,
    val age: Int?,
    val phoneNumber: String?,
    val username: String?,
    val password: String?
)