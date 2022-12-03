package com.arifwidayana.challengechapter7.di

import com.arifwidayana.challengechapter7.base.arch.BaseGenericViewModel
import com.arifwidayana.challengechapter7.data.repository.*
import com.arifwidayana.challengechapter7.presentation.ui.auth.forget.ForgetPasswordViewModel
import com.arifwidayana.challengechapter7.presentation.ui.auth.forget.newpassword.NewPasswordViewModel
import com.arifwidayana.challengechapter7.presentation.ui.auth.login.LoginViewModel
import com.arifwidayana.challengechapter7.presentation.ui.auth.register.RegisterViewModel
import com.arifwidayana.challengechapter7.presentation.ui.homepage.detailmovie.DetailMovieViewModel
import com.arifwidayana.challengechapter7.presentation.ui.homepage.home.HomeViewModel
import com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.edit.EditProfileViewModel
import com.arifwidayana.challengechapter7.presentation.ui.homepage.profile.user.ProfileUserViewModel
import com.arifwidayana.challengechapter7.presentation.ui.boarding.OnBoardingViewModel
import com.arifwidayana.challengechapter7.presentation.ui.homepage.favorite.FavoriteViewModel
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
    fun provideOnBoardingViewModel(onBoardingRepository: OnBoardingRepository): OnBoardingViewModel {
        return BaseGenericViewModel(OnBoardingViewModel(onBoardingRepository)).create(
            OnBoardingViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideLoginViewModel(loginRepository: LoginRepository): LoginViewModel {
        return BaseGenericViewModel(LoginViewModel(loginRepository)).create(
            LoginViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideRegisterViewModel(registerRepository: RegisterRepository): RegisterViewModel {
        return BaseGenericViewModel(RegisterViewModel(registerRepository)).create(
            RegisterViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideForgetPasswordViewModel(forgetPasswordRepository: ForgetPasswordRepository): ForgetPasswordViewModel {
        return BaseGenericViewModel(ForgetPasswordViewModel(forgetPasswordRepository)).create(
            ForgetPasswordViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideNewPasswordViewModel(newPasswordRepository: NewPasswordRepository): NewPasswordViewModel {
        return BaseGenericViewModel(NewPasswordViewModel(newPasswordRepository)).create(
            NewPasswordViewModel::class.java
        )
    }

            @Provides
    @FragmentScoped
    fun provideHomeViewModel(homepageRepository: HomeRepository): HomeViewModel {
        return BaseGenericViewModel(HomeViewModel(homepageRepository)).create(
            HomeViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideDetailMovieViewModel(detailMovieRepository: DetailMovieRepository): DetailMovieViewModel {
        return BaseGenericViewModel(DetailMovieViewModel(detailMovieRepository)).create(
            DetailMovieViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideFavoriteViewModel(favoriteRepository: FavoriteRepository): FavoriteViewModel {
        return BaseGenericViewModel(FavoriteViewModel(favoriteRepository)).create(
            FavoriteViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideProfileUserViewModel(profileUserRepository: ProfileUserRepository): ProfileUserViewModel {
        return BaseGenericViewModel(ProfileUserViewModel(profileUserRepository)).create(
            ProfileUserViewModel::class.java
        )
    }

    @Provides
    @FragmentScoped
    fun provideEditProfileUser(editProfileRepository: EditProfileRepository): EditProfileViewModel {
        return BaseGenericViewModel(EditProfileViewModel(editProfileRepository)).create(
            EditProfileViewModel::class.java
        )
    }
}