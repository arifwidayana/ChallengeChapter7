package com.arifwidayana.challengechapter7.di

import android.content.Context
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSourceImpl
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSource
import com.arifwidayana.challengechapter7.data.local.model.dao.UserDao
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSourceImpl
import com.arifwidayana.challengechapter7.data.local.model.dao.FavoriteDao
import com.arifwidayana.challengechapter7.data.network.datasource.MovieDataSource
import com.arifwidayana.challengechapter7.data.network.datasource.MovieDataSourceImpl
import com.arifwidayana.challengechapter7.data.network.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(userDao: UserDao, favoriteDao: FavoriteDao): LocalDataSource {
        return LocalDataSourceImpl(userDao, favoriteDao)
    }

    @Provides
    @Singleton
    fun provideUserPreferenceDataSource(context: Context): UserPreferenceDataSource {
        return UserPreferenceDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideMoviesDataSource(movieService: MovieService): MovieDataSource {
        return MovieDataSourceImpl(movieService)
    }
}