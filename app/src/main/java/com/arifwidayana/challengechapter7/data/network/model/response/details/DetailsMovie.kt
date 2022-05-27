package com.arifwidayana.challengechapter7.data.network.model.response.details

import com.google.gson.annotations.SerializedName

data class DetailsMovie(
    @SerializedName("genres")
    val genres: List<GenreMovie>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)