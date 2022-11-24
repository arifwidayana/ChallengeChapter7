package com.arifwidayana.challengechapter7.presentation.ui.splashscreen

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.databinding.FragmentSplashScreenBinding
import com.arifwidayana.challengechapter7.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding, SplashScreenViewModel>(
    FragmentSplashScreenBinding::inflate
) {
    override fun initView() {
        onView()
    }

    private fun onView() {
        viewModelInstance.getBoarding()
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModelInstance.getBoardingResult.collect {
                    if (it is Resource.Success) {
                        when (it.data) {
                            !true -> moveNav(R.id.action_splashScreen_to_onBoarding)
                            else -> viewModelInstance.getUsername()
                        }
                    }
                }
            }

            launchWhenStarted {
                viewModelInstance.getUsernameResult.collect {
                    if (it is Resource.Success) {
                        when (it.data) {
                            Constant.USERNAME_PREF -> {
                                moveNav(R.id.action_splashScreen_to_loginFragment)
                            }
                            else -> {
                                moveNav(R.id.action_splashScreen_to_homeFragment)
                            }
                        }
                    }
                }
            }
        }
    }
}