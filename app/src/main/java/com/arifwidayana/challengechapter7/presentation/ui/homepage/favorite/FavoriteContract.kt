package com.arifwidayana.challengechapter7.presentation.ui.homepage.favorite

import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.entity.FavoriteEntity
import kotlinx.coroutines.flow.StateFlow

interface FavoriteContract {
    val getFavoriteMovieResult: StateFlow<Resource<List<FavoriteEntity>>>
    fun getFavoriteMovie()
}