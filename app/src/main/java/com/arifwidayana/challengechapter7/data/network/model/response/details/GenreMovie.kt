package com.arifwidayana.challengechapter7.data.network.model.response.details

import com.google.gson.annotations.SerializedName

data class GenreMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)