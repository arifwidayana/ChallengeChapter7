package com.arifwidayana.challengechapter7.di

import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSourcelmpl
import com.arifwidayana.challengechapter7.data.local.model.response.UserDao
import com.arifwidayana.challengechapter7.data.local.preference.DatastorePreference
import com.arifwidayana.challengechapter7.data.network.datasource.MoviesDataSource
import com.arifwidayana.challengechapter7.data.network.datasource.MoviesDataSourcelmpl
import com.arifwidayana.challengechapter7.data.network.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideMoviesDataSource(movieService: MovieService): MoviesDataSource {
        return MoviesDataSourcelmpl(movieService)
    }
    @Singleton
    @Provides
    fun provideLocalDataSource(userDao: UserDao, datastorePreference: DatastorePreference): LocalDataSource {
        return LocalDataSourcelmpl(userDao, datastorePreference)
    }
}