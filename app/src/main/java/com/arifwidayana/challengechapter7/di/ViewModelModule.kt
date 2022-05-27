package com.arifwidayana.challengechapter7.di

import com.arifwidayana.challengechapter7.base.arch.GenericViewModelFactory
import com.arifwidayana.challengechapter7.ui.auth.login.LoginRepository
import com.arifwidayana.challengechapter7.ui.auth.login.LoginViewModel
import com.arifwidayana.challengechapter7.ui.auth.register.RegisterRepository
import com.arifwidayana.challengechapter7.ui.auth.register.RegisterViewModel
import com.arifwidayana.challengechapter7.ui.homepage.detailsmovie.DetailMoviesRepository
import com.arifwidayana.challengechapter7.ui.homepage.detailsmovie.DetailMoviesViewModel
import com.arifwidayana.challengechapter7.ui.homepage.home.HomeRepository
import com.arifwidayana.challengechapter7.ui.homepage.home.HomeViewModel
import com.arifwidayana.challengechapter7.ui.homepage.profile.edit.EditProfileRepository
import com.arifwidayana.challengechapter7.ui.homepage.profile.edit.EditProfileViewModel
import com.arifwidayana.challengechapter7.ui.homepage.profile.user.ProfileUserRepository
import com.arifwidayana.challengechapter7.ui.homepage.profile.user.ProfileUserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object ViewModelModule {
    @Provides
    @FragmentScoped
    fun movieListViewModel(homepageRepository: HomeRepository): HomeViewModel {
        return GenericViewModelFactory(HomeViewModel(homepageRepository)).create(
            HomeViewModel::class.java
        )
    }
    @Provides
    @FragmentScoped
    fun movieDetailViewModel(detailMoviesRepository: DetailMoviesRepository): DetailMoviesViewModel {
        return GenericViewModelFactory(DetailMoviesViewModel(detailMoviesRepository)).create(
            DetailMoviesViewModel::class.java
        )
    }
    @Provides
    @FragmentScoped
    fun loginViewModel(loginRepository: LoginRepository): LoginViewModel {
        return GenericViewModelFactory(LoginViewModel(loginRepository)).create(
            LoginViewModel::class.java
        )
    }
    @Provides
    @FragmentScoped
    fun registerViewModel(registerRepository: RegisterRepository): RegisterViewModel {
        return GenericViewModelFactory(RegisterViewModel(registerRepository)).create(
            RegisterViewModel::class.java
        )
    }
    @Provides
    @FragmentScoped
    fun profileUserViewModel(profileUserRepository: ProfileUserRepository): ProfileUserViewModel {
        return GenericViewModelFactory(ProfileUserViewModel(profileUserRepository)).create(
            ProfileUserViewModel::class.java
        )
    }
    @Provides
    @FragmentScoped
    fun editProfileUser(editProfileRepository: EditProfileRepository): EditProfileViewModel {
        return GenericViewModelFactory(EditProfileViewModel(editProfileRepository)).create(
            EditProfileViewModel::class.java
        )
    }
}