package com.arifwidayana.challengechapter7.data.local.model.entity

import androidx.room.Entity

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    var idMovie: Int,
    var username: String
)
