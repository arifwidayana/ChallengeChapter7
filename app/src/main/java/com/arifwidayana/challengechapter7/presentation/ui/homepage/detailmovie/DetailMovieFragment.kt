package com.arifwidayana.challengechapter7.presentation.ui.homepage.detailmovie

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.network.model.response.movie.details.DetailMovieResponse
import com.arifwidayana.challengechapter7.databinding.FragmentDetailMovieBinding
import com.arifwidayana.challengechapter7.utils.Constant
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding, DetailMovieViewModel>(
    FragmentDetailMovieBinding::inflate
) {
    private val args by navArgs<DetailMovieFragmentArgs>()

    override fun initView() {
        onView()
        onClick()
    }

    private fun onView() {
        viewModelInstance.apply {
            getMovieDetail(args.id)
            favMovie()
        }
    }

    private fun onClick() {
        binding.apply {
            ivBack.setOnClickListener {
                moveNav()
            }
            ibFavorite.setOnClickListener {
                viewModelInstance.stateFavorite(args.id)
            }
        }
    }

    private fun favMovie() {
        viewModelInstance.getFavoriteById(args.id)
    }

    override fun showLoading(isVisible: Boolean) {
        binding.pbDetailMovies.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        binding.clDisplay.isVisible = isVisible
    }

    override fun observeData() {
        lifecycleScope.apply {
            launchWhenStarted {
                viewModelInstance.getMovieDetailResult.collect {
                    when (it) {
                        is Resource.Empty -> {}
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            setContentData(it.data)
                        }
                        is Resource.Error -> {}
                    }
                }
            }
            launchWhenStarted {
                viewModelInstance.getFavoriteByIdResult.collect {
                    if (it is Resource.Success) {
                        stateFavorite(it.data)
                    }
                }
            }

            launchWhenStarted {
                viewModelInstance.insertFavoriteResult.collect {
                    if (it is Resource.Success) {
                        showMessageSnackBar(true, it.message)
                        favMovie()
                    }
                }
            }

            launchWhenStarted {
                viewModelInstance.deleteFavoriteByIdResult.collect {
                    if (it is Resource.Success) {
                        showMessageSnackBar(true, it.message)
                        favMovie()
                    }
                }
            }
        }
    }

    private fun stateFavorite(data: Boolean?) {
        when (data) {
            true -> setStateFav(R.drawable.ic_favorite_saved)
            else -> setStateFav(R.drawable.ic_favorite_not_saved)
        }
    }

    private fun setStateFav(icSelectorFav: Int) {
        binding.apply {
            ibFavorite.setImageResource(icSelectorFav)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setContentData(data: DetailMovieResponse?) {
        binding.apply {
            data?.let {
                val listGenre = it.genres?.joinToString { data -> data?.name.toString() }
                Glide.with(root)
                    .load("${Constant.BASE_IMAGE}${it.posterPath}")
                    .into(ivPoster)
                tvStatus.text = data.status
                tvTitle.text = data.title
                tvGenre.text = "Genre: $listGenre"
                tvRating.text = "Rating: ${data.voteAverage}"
                tvReleaseDate.text = "Release Date: ${data.releaseDate}"
                tvOverview.text = data.overview
            }
        }
    }
}