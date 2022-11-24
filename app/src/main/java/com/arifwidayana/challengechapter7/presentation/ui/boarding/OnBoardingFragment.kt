package com.arifwidayana.challengechapter7.presentation.ui.boarding

import androidx.lifecycle.ViewModel
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding, ViewModel>(
    FragmentOnBoardingBinding::inflate
) {
    override fun initView() {
        onView()
    }

    private fun onView() {
        binding.apply {
            val adapterBoard = OnBoardingAdapter(
                requireActivity().supportFragmentManager,
                lifecycle
            )
            vpOnBoarding.adapter = adapterBoard
        }
    }

    override fun observeData() { }
}