package com.arifwidayana.challengechapter7.presentation.ui.homepage.detailsmovie

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.databinding.FragmentDetailMoviesBinding
import com.arifwidayana.challengechapter7.utils.Constant
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMoviesFragment : BaseFragment<FragmentDetailMoviesBinding, DetailMoviesViewModel>(
    FragmentDetailMoviesBinding::inflate
), DetailMoviesContract.View {
    private val args by navArgs<DetailMoviesFragmentArgs>()

    override fun initView() {
        onClickEvent()
        getIntentData()
    }

    private fun onClickEvent() {
        getViewBinding().ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_detailMoviesFragment_to_homeFragment)
        }
    }

    override fun getIntentData() {
        getViewModel().setIntentData(args.id)
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().pbDetailMovies.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().clDisplay.isVisible = isVisible
    }

    override fun observeData() {
        getViewModel().apply {
            getNowPlayingDetailResponse().observe(viewLifecycleOwner) {
                when(it) {
                    is Resource.Loading -> {
                        showLoading(true)
                        showContent(false)
                        showMessageToast(false)
                    }
                    is Resource.Success -> {
                        showLoading(false)
                        showContent(true)
                        showMessageToast(false)
                        it.data?.let { data ->
                            setContentData(data)
                        }
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        showContent(true)
                        showMessageToast(true, it.message)
                    }
                }
            }
            getMovieId().observe(viewLifecycleOwner) {
                it?.let {
                    getViewModel().getMovieDetail(it)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setContentData(data: DetailsMovie) {
        val listGenre = arrayListOf<String>()

        getViewBinding().apply {
            data.genres.forEach {
                listGenre.add(it.name)
            }
            Glide.with(root)
                .load("${Constant.BASE_IMAGE}${data.posterPath}")
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