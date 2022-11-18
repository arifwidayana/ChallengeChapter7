package com.arifwidayana.challengechapter7.presentation.ui.boarding.screen

import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.databinding.FragmentSecondScreenBinding

class SecondScreenFragment : BaseFragment<FragmentSecondScreenBinding, ViewModel>(
    FragmentSecondScreenBinding::inflate
) {
    override fun initView() {
        onView()
    }

    private fun onView() {
        binding.apply {
            tvNext.setOnClickListener {
                activity?.findViewById<ViewPager2>(R.id.vp_on_boarding)?.currentItem = 2
            }
        }
    }

    override fun observeData() { }
}