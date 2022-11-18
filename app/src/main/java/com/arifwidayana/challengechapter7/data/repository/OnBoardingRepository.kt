package com.arifwidayana.challengechapter7.data.repository

import com.arifwidayana.challengechapter7.base.arch.BaseRepository
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.datasource.UserPreferenceDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface OnBoardingRepository {
    suspend fun setBoarding(boardStatus: Boolean): Flow<Resource<Unit>>
}

class OnBoardingRepositoryImpl @Inject constructor(
    private val userPreferenceDataSource: UserPreferenceDataSource
): OnBoardingRepository, BaseRepository() {
    override suspend fun setBoarding(boardStatus: Boolean): Flow<Resource<Unit>> = flow {
        emit(proceed { userPreferenceDataSource.setBoarding(boardStatus) })
    }
}