package com.arifwidayana.challengechapter7.data.network.model.response.listmovies

import com.google.gson.annotations.SerializedName

class ListPlayingMovie {
    data class ListPlayingMovie(
        @SerializedName("results")
        val results: List<ResultListPlaying>
    )
}