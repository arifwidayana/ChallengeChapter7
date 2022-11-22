package com.arifwidayana.challengechapter7.di

import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSource
import com.arifwidayana.challengechapter7.data.network.datasource.MovieDataSource
import com.arifwidayana.challengechapter7.data.repository.*
import com.arifwidayana.challengechapter7.data.repository.RegisterRepository
import com.arifwidayana.challengechapter7.presentation.ui.homepage.detailsmovie.DetailMoviesRepository
import com.arifwidayana.challengechapter7.data.repository.HomeRepository
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
    @Provides
    @Singleton
    fun provideSplashScreenRepository(userPreferenceDataSource: UserPreferenceDataSource): SplashScreenRepository {
        return SplashScreenRepositoryImpl(userPreferenceDataSource)
    }

    @Provides
    @Singleton
    fun provideOnBoardingRepository(userPreferenceDataSource: UserPreferenceDataSource): OnBoardingRepository {
        return OnBoardingRepositoryImpl(userPreferenceDataSource)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(localDataSource: LocalDataSource): LoginRepository {
        return LoginRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(localDataSource: LocalDataSource): RegisterRepository {
        return RegisterRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        userPreferenceDataSource: UserPreferenceDataSource,
        localDataSource: LocalDataSource,
        movieDataSource: MovieDataSource
    ): HomeRepository {
        return HomeRepositoryImpl(userPreferenceDataSource, localDataSource, movieDataSource)
    }

    @Singleton
    @Provides
    fun provideMovieDetailsRepository(movieDataSource: MovieDataSource): DetailMoviesRepository {
        return DetailMoviesRepository(movieDataSource)
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