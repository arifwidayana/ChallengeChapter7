package com.arifwidayana.challengechapter7.data.local.model

data class UserPreference(
    val username: String,
    val loginSession: Boolean,
    val onBoardSession: Boolean
)