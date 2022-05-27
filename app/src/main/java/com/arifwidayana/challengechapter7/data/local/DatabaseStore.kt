package com.arifwidayana.challengechapter7.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arifwidayana.challengechapter7.data.local.model.response.UserDao
import com.arifwidayana.challengechapter7.data.local.model.response.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseStore : RoomDatabase() {
    abstract fun userDao(): UserDao
//    abstract fun favoriteDao(): FavoriteDao
}