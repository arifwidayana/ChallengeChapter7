package com.arifwidayana.challengechapter7.di

import com.arifwidayana.challengechapter7.data.local.datasource.LocalDataSource
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSource
import com.arifwidayana.challengechapter7.data.network.datasource.MovieDataSource
import com.arifwidayana.challengechapter7.data.repository.*
import com.arifwidayana.challengechapter7.data.repository.RegisterRepository
import com.arifwidayana.challengechapter7.data.repository.DetailMovieRepository
import com.arifwidayana.challengechapter7.data.repository.HomeRepository
import com.arifwidayana.challengechapter7.data.repository.EditProfileRepository
import com.arifwidayana.challengechapter7.data.repository.ProfileUserRepository
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
    fun provideLoginRepository(
        userPreferenceDataSource: UserPreferenceDataSource,
        localDataSource: LocalDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(userPreferenceDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(localDataSource: LocalDataSource): RegisterRepository {
        return RegisterRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideForgetPasswordRepository(localDataSource: LocalDataSource): ForgetPasswordRepository {
        return ForgetPasswordRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideNewPasswordRepository(localDataSource: LocalDataSource): NewPasswordRepository {
        return NewPasswordRepositoryImpl(localDataSource)
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

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(
        userPreferenceDataSource: UserPreferenceDataSource,
        localDataSource: LocalDataSource,
        movieDataSource: MovieDataSource,
    ): DetailMovieRepository {
        return DetailMovieRepositoryImpl(userPreferenceDataSource, localDataSource, movieDataSource)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        userPreferenceDataSource: UserPreferenceDataSource,
        localDataSource: LocalDataSource
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(userPreferenceDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideProfileUserRepository(
        userPreferenceDataSource: UserPreferenceDataSource,
        localDataSource: LocalDataSource
    ): ProfileUserRepository {
        return ProfileUserRepositoryImpl(userPreferenceDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideEditProfileRepository(
        userPreferenceDataSource: UserPreferenceDataSource,
        localDataSource: LocalDataSource
    ): EditProfileRepository {
        return EditProfileRepositoryImpl(userPreferenceDataSource, localDataSource)
    }
}