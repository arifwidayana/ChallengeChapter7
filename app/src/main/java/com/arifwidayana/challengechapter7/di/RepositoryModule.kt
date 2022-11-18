package com.arifwidayana.challengechapter7.di

import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.network.datasource.MovieDataSource
import com.arifwidayana.challengechapter7.presentation.ui.auth.login.LoginRepository
import com.arifwidayana.challengechapter7.presentation.ui.auth.register.RegisterRepository
import com.arifwidayana.challengechapter7.presentation.ui.homepage.detailsmovie.DetailMoviesRepository
import com.arifwidayana.challengechapter7.presentation.ui.homepage.home.HomeRepository
import com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.edit.EditProfileRepository
import com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.user.ProfileUserRepository
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
    fun provideMovieListRepository(movieDataSource: MovieDataSource, localDataSource: LocalDataSource): HomeRepository {
        return HomeRepository(movieDataSource, localDataSource)
    }
    @Singleton
    @Provides
    fun provideMovieDetailsRepository(movieDataSource: MovieDataSource): DetailMoviesRepository {
        return DetailMoviesRepository(movieDataSource)
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