package com.arifwidayana.challengechapter7.data.local.model.response

data class UserPreference(
    val username: String,
    val loginSession: Boolean,
    val onBoardSession: Boolean
)