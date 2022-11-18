package com.arifwidayana.challengechapter7.data.local.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arifwidayana.challengechapter7.data.local.model.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_table WHERE username = :username")
    fun getFavorite(username: String): Flow<List<FavoriteEntity>>

    @Query("DELETE FROM favorite_table WHERE idMovie = :idMovie")
    suspend fun deleteFavorite(idMovie: Int)
}