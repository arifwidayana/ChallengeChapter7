package com.arifwidayana.challengechapter7.di

import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.network.datasource.MoviesDataSource
import com.arifwidayana.challengechapter7.ui.auth.login.LoginRepository
import com.arifwidayana.challengechapter7.ui.auth.register.RegisterRepository
import com.arifwidayana.challengechapter7.ui.homepage.detailsmovie.DetailMoviesRepository
import com.arifwidayana.challengechapter7.ui.homepage.home.HomeRepository
import com.arifwidayana.challengechapter7.ui.homepage.profile.edit.EditProfileRepository
import com.arifwidayana.challengechapter7.ui.homepage.profile.user.ProfileUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMovieListRepository(moviesDataSource: MoviesDataSource, localDataSource: LocalDataSource): HomeRepository {
        return HomeRepository(moviesDataSource, localDataSource)
    }
    @Singleton
    @Provides
    fun provideMovieDetailsRepository(moviesDataSource: MoviesDataSource): DetailMoviesRepository {
        return DetailMoviesRepository(moviesDataSource)
    }
    @Singleton
    @Provides
    fun provideLoginRepository(localDataSource: LocalDataSource): LoginRepository {
        return LoginRepository(localDataSource)
    }
    @Singleton
    @Provides
    fun provideRegisterRepository(localDataSource: LocalDataSource): RegisterRepository {
        return RegisterRepository(localDataSource)
    }
    @Singleton
    @Provides
    fun provideProfileUserRepository(localDataSource: LocalDataSource): ProfileUserRepository {
        return ProfileUserRepository(localDataSource)
    }
    @Singleton
    @Provides
    fun provideEditProfileRepository(localDataSource: LocalDataSource): EditProfileRepository {
        return EditProfileRepository(localDataSource)
    }
}