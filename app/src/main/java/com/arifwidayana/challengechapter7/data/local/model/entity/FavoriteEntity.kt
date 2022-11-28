package com.arifwidayana.challengechapter7.data.local.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey
    var idMovie: Int,
    var username: String,
    var isFavorite: Boolean
)
