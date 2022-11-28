package com.arifwidayana.challengechapter7.presentation.ui.homepage.favorite

import androidx.lifecycle.lifecycleScope
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.local.model.entity.FavoriteEntity
import com.arifwidayana.challengechapter7.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(
    FragmentFavoriteBinding::inflate
) {
    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModelInstance.getFavoriteMovie()
    }

    private fun onClick() {
        binding.apply {
            ivBack.setOnClickListener {
                moveNav()
            }
        }
    }

    override fun observeData() {
        lifecycleScope.launchWhenStarted {
            viewModelInstance.getFavoriteMovieResult.collect {
                if (it is Resource.Success) {
                    setDataAdapter(it.data)
                }
            }
        }
    }

    private fun setDataAdapter(data: List<FavoriteEntity>?) {
        binding.apply {
            val adapterFavorite = FavoriteAdapter {
                moveNav(
                    FavoriteFragmentDirections
                    .actionFavoriteFragmentToDetailMovieFragment()
                    .setId(it.idMovie)
                )
            }
            adapterFavorite.submitList(data)
            rvListFavorite.adapter = adapterFavorite
        }
    }
}