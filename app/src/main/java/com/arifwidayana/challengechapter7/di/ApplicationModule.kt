package com.arifwidayana.challengechapter7.di

import android.content.Context
import com.arifwidayana.challengechapter7.data.local.preference.DatastorePreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Singleton
    @Provides
    fun provideDataStorePreference(
        @ApplicationContext context: Context
    ): DatastorePreference{
        return DatastorePreference(context)
    }
}