package com.arifwidayana.challengechapter7.di

import android.content.Context
import com.arifwidayana.challengechapter7.data.network.service.MovieService
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideMovieService(chuckerInterceptor: ChuckerInterceptor): MovieService {
        return MovieService.invoke(chuckerInterceptor)
    }
    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }
}