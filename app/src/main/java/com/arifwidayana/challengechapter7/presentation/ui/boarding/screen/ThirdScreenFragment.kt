package com.arifwidayana.challengechapter7.presentation.ui.boarding.screen

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.databinding.FragmentThirdScreenBinding
import com.arifwidayana.challengechapter7.presentation.ui.boarding.OnBoardingViewModel

class ThirdScreenFragment : BaseFragment<FragmentThirdScreenBinding, OnBoardingViewModel>(
    FragmentThirdScreenBinding::inflate
) {
    override fun initView() {
        onClick()
    }

    private fun onClick() {
        binding.btnStart.setOnClickListener {
            viewModelInstance.setBoarding()
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModelInstance.setBoardingResult.collect {
                if (it is Resource.Success) {
                    findNavController().navigate(R.id.action_onBoarding_to_loginFragment)
                }
            }
        }
    }
}