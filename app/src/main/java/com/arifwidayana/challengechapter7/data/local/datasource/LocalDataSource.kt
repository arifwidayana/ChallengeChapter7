package com.arifwidayana.challengechapter7.data.local.datasource

import com.arifwidayana.challengechapter7.data.local.model.dao.FavoriteDao
import com.arifwidayana.challengechapter7.data.local.model.dao.UserDao
import com.arifwidayana.challengechapter7.data.local.model.entity.FavoriteEntity
import com.arifwidayana.challengechapter7.data.local.model.entity.UserEntity
import com.arifwidayana.challengechapter7.data.local.model.request.EditProfileRequest
import com.arifwidayana.challengechapter7.data.local.model.request.LoginRequest
import com.arifwidayana.challengechapter7.data.local.model.request.ProfileUserRequest
import com.arifwidayana.challengechapter7.data.local.model.request.RegisterRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalDataSource {
    suspend fun registerUser(registerRequest: RegisterRequest)
    suspend fun loginUser(loginRequest: LoginRequest): Flow<UserEntity>
    suspend fun getUser(username: String): Flow<UserEntity>
    suspend fun updateImageProfile(profileUserRequest: ProfileUserRequest)
    suspend fun updateProfileUser(username: String, editProfileRequest: EditProfileRequest)
    suspend fun insertMovie(favoriteEntity: FavoriteEntity)
    suspend fun getFavoriteMovie(username: String): Flow<List<FavoriteEntity>>
    suspend fun deleteFavorite(movieId: Int)
}

class LocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val favoriteDao: FavoriteDao
): LocalDataSource {
    override suspend fun registerUser(registerRequest: RegisterRequest) {
        return userDao.insertUser(
            with(registerRequest) {
                UserEntity(
                    id = null,
                    name = name,
                    imageProfile = imageProfile,
                    email = email,
                    age = age,
                    phoneNumber = phoneNumber,
                    username = username,
                    password = password
                )
            }
        )
    }

    override suspend fun loginUser(loginRequest: LoginRequest): Flow<UserEntity> {
        return userDao.loginUser(
            username = loginRequest.username,
            password = loginRequest.password
        )
    }

    override suspend fun getUser(username: String): Flow<UserEntity> {
        return userDao.getUser(username)
    }

    override suspend fun updateImageProfile(profileUserRequest: ProfileUserRequest) {
        return userDao.updateImageProfile(
            imageProfile = profileUserRequest.imageProfile,
            username = profileUserRequest.usernameUser
        )
    }

    override suspend fun updateProfileUser(username: String, editProfileRequest: EditProfileRequest) {
        return userDao.updateProfileUser(
            username = username,
            name = editProfileRequest.name,
            email = editProfileRequest.email,
            age = editProfileRequest.age,
            phoneNumber = editProfileRequest.phoneNumber
        )
    }

    override suspend fun insertMovie(favoriteEntity: FavoriteEntity) {
        return favoriteDao.insertMovie(favoriteEntity)
    }

    override suspend fun getFavoriteMovie(username: String): Flow<List<FavoriteEntity>> {
        return favoriteDao.getFavorite(username)
    }

    override suspend fun deleteFavorite(movieId: Int) {
        return favoriteDao.deleteFavorite(movieId)
    }
}