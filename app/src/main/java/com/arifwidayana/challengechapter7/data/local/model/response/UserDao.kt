package com.arifwidayana.challengechapter7.data.local.model.response

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table WHERE username_user = :username AND password_user = :password LIMIT 1")
    fun loginUser(username: String?, password: String?): UserEntity

    @Query("SELECT EXISTS (SELECT 1 FROM user_table WHERE username_user = :username)")
    fun checkUser(username: String): Int

    @Query("SELECT * FROM user_table WHERE username_user = :username LIMIT 1")
    fun getUser(username: String?): UserEntity

    @Update
    suspend fun updateProfileUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity): Int
}