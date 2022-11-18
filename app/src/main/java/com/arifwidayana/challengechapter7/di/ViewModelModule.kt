package com.arifwidayana.challengechapter7.di

import com.arifwidayana.challengechapter7.base.arch.BaseGenericViewModel
import com.arifwidayana.challengechapter7.data.repository.SplashScreenRepository
import com.arifwidayana.challengechapter7.presentation.ui.auth.login.LoginRepository
import com.arifwidayana.challengechapter7.presentation.ui.auth.login.LoginViewModel
import com.arifwidayana.challengechapter7.presentation.ui.auth.register.RegisterRepository
import com.arifwidayana.challengechapter7.presentation.ui.auth.register.RegisterViewModel
import com.arifwidayana.challengechapter7.presentation.ui.homepage.detailsmovie.DetailMoviesRepository
import com.arifwidayana.challengechapter7.presentation.ui.homepage.detailsmovie.DetailMoviesViewModel
import com.arifwidayana.challengechapter7.presentation.ui.homepage.home.HomeRepository
import com.arifwidayana.challengechapter7.presentation.ui.homepage.home.HomeViewModel
import com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.edit.EditProfileRepository
import com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.edit.EditProfileViewModel
import com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.user.ProfileUserRepository
import com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.user.ProfileUserViewModel
import com.arifwidayana.challengechapter7.presentation.ui.splashscreen.SplashScreenViewModel
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
    fun provideSplashScreenViewModel(splashScreenRepository: SplashScreenRepository): SplashScreenViewModel {
        return BaseGenericViewModel(SplashScreenViewModel(splashScreenRepository)).create(
            SplashScreenViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun movieListViewModel(homepageRepository: HomeRepository): HomeViewModel {
        return BaseGenericViewModel(HomeViewModel(homepageRepository)).create(
            HomeViewModel::class.java
        )
    }
    @Provides
    @FragmentScoped
    fun movieDetailViewModel(detailMoviesRepository: DetailMoviesRepository): DetailMoviesViewModel {
        return BaseGenericViewModel(DetailMoviesViewModel(detailMoviesRepository)).create(
            DetailMoviesViewModel::class.java
        )
    }
    @Provides
    @FragmentScoped
    fun loginViewModel(loginRepository: LoginRepository): LoginViewModel {
        return BaseGenericViewModel(LoginViewModel(loginRepository)).create(
            LoginViewModel::class.java
        )
    }
    @Provides
    @FragmentScoped
    fun registerViewModel(registerRepository: RegisterRepository): RegisterViewModel {
        return BaseGenericViewModel(RegisterViewModel(registerRepository)).create(
            RegisterViewModel::class.java
        )
    }
    @Provides
    @FragmentScoped
    fun profileUserViewModel(profileUserRepository: ProfileUserRepository): ProfileUserViewModel {
        return BaseGenericViewModel(ProfileUserViewModel(profileUserRepository)).create(
            ProfileUserViewModel::class.java
        )
    }
    @Provides
    @FragmentScoped
    fun editProfileUser(editProfileRepository: EditProfileRepository): EditProfileViewModel {
        return BaseGenericViewModel(EditProfileViewModel(editProfileRepository)).create(
            EditProfileViewModel::class.java
        )
    }
}