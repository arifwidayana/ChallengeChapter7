package com.arifwidayana.challengechapter7.data.local.model.request

data class FavoriteRequest(
    val idMovie: Int,
    val username: String,
    val isFavorite: Boolean
)