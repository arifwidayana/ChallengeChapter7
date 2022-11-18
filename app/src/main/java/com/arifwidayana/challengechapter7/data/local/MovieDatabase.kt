package com.arifwidayana.challengechapter7.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arifwidayana.challengechapter7.data.local.model.dao.FavoriteDao
import com.arifwidayana.challengechapter7.data.local.model.dao.UserDao
import com.arifwidayana.challengechapter7.data.local.model.entity.FavoriteEntity
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity

@Database(
    entities = [UserEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun favoriteDao(): FavoriteDao
}