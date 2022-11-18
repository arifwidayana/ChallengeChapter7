package com.arifwidayana.challengechapter7.presentation.ui.splashscreen

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
                    when (it is Resource.Success) {
                        !true -> {
                            moveNavFragment(R.id.action_splashScreen_to_onBoarding)
                        }
                        else -> {
                            viewModelInstance.getUsername()
                        }
                    }
                }
            }

            launchWhenStarted {
                viewModelInstance.getUsernameResult.collect {
                    if(it is Resource.Success) {
                        if(it.data == Constant.USERNAME_PREF) {
                            moveNavFragment(R.id.action_splashScreen_to_loginFragment)
                        } else {
                            moveNavFragment(R.id.action_splashScreen_to_homeFragment)
                        }
                    }
                }
            }
        }
    }

    private fun moveNavFragment(navUp: Int) {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(navUp)
        }, 3000)
    }
}