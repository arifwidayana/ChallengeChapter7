package com.arifwidayana.challengechapter7.data.network.model.response.listmovies

import com.google.gson.annotations.SerializedName

data class ResultListPlaying(
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)