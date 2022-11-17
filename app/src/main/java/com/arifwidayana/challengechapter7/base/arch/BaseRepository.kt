package com.arifwidayana.challengechapter7.base.arch

import com.arifwidayana.challengechapter7.base.model.Resource

class BaseRepository {
    suspend fun <T> proceed(coroutine: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(coroutine.invoke())
        } catch (exception: Exception) {
            Resource.Error(exception)
        }
    }
}