package com.arifwidayana.challengechapter7.data.local.model.dao

import androidx.room.*
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE username_user = :username AND password_user = :password")
    fun loginUser(username: String?, password: String?): Flow<UserEntity>

    @Query("SELECT * FROM user_table WHERE username_user = :username")
    fun getUser(username: String?): Flow<UserEntity>

    @Update
    suspend fun updateProfileUser(user: UserEntity)

//    @Delete
//    suspend fun deleteUser(user: UserEntity)
}