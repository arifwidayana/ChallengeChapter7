package com.arifwidayana.challengechapter7.di

import android.content.Context
import androidx.room.Room
import com.arifwidayana.challengechapter7.data.local.DatabaseStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): DatabaseStore = Room
        .databaseBuilder(
            context,
            DatabaseStore::class.java,
            "data.db"
        )
        .build()

    @Singleton
    @Provides
    fun provideUserDao(databaseStore: DatabaseStore) = databaseStore.userDao()

//    @Singleton
//    @Provides
//    fun provideFavoriteDao(databaseStore: DatabaseStore) = databaseStore.favoriteDao()
}