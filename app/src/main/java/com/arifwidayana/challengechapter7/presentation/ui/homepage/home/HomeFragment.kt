package com.arifwidayana.challengechapter7.presentation.ui.homepage.home

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.arifwidayana.challengechapter7.R
import com.arifwidayana.challengechapter7.base.arch.BaseFragment
import com.arifwidayana.challengechapter7.base.model.Resource
import com.arifwidayana.challengechapter7.data.network.model.response.movie.ResultListPlaying
import com.arifwidayana.challengechapter7.databinding.FragmentHomeBinding
import com.arifwidayana.challengechapter7.presentation.ui.adapter.ListPlayingMoviesAdapter
import com.arifwidayana.challengechapter7.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
), HomeContract.View {
    private lateinit var shared: SharedHelper

    override fun initView() {
        shared = SharedHelper(requireContext())
        getData()
        onClick()
    }

    override fun getData() {
        getViewModel().apply {
            getMovieListNowPlaying()
            getMovieListPopular()
            getMovieListUpComing()
            getMovieListTopRated()
            getUser(shared.getString(Constant.USERNAME_PREF).toString())
        }
    }

    private fun onClick() {
        getViewBinding().apply {
            ivFavorite.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            }

            ivProfile.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileUserFragment)
            }

        }
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().pbMovies.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().clDisplay.isVisible = isVisible
    }

    @SuppressLint("SetTextI18n")
    override fun observeData() {
        getViewModel().apply {
            getUserResult().observe(viewLifecycleOwner) {
                getViewBinding().tvShowName.text = "Hi, ${it.name}"
            }

            getNowPlayingResultLiveData().observe(viewLifecycleOwner) {
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
                        setDataNowPlayingAdapter(it.data?.results)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        showContent(true)
                        showMessageToast(true, it.message)
                    }
                }
            }

            getPopularResultLiveData().observe(viewLifecycleOwner) {
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
                        setDataPopularAdapter(it.data?.results)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        showContent(true)
                        showMessageToast(true, it.message)
                    }
                }
            }

            getUpComingResultLiveData().observe(viewLifecycleOwner) {
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
                        setDataUpComingAdapter(it.data?.results)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        showContent(true)
                        showMessageToast(true, it.message)
                    }
                }
            }

            getTopRatedResultLiveData().observe(viewLifecycleOwner) {
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
                        setDataTopRatedAdapter(it.data?.results)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        showContent(true)
                        showMessageToast(true, it.message)
                    }
                }
            }
        }
    }

    private fun setDataNowPlayingAdapter(results: List<ResultListPlaying>?) {
        getViewBinding().apply {
            val adapter = ListPlayingMoviesAdapter {
                val parcel = HomeFragmentDirections.actionHomeFragmentToDetailMoviesFragment()
                parcel.id = it.id
                findNavController().navigate(parcel)
            }
            adapter.submitList(results)
            rvMovieNowPlaying.adapter = adapter
        }
    }

    private fun setDataPopularAdapter(results: List<ResultListPlaying>?) {
        getViewBinding().apply {
            val adapter = ListPlayingMoviesAdapter {
                val parcel = HomeFragmentDirections.actionHomeFragmentToDetailMoviesFragment()
                parcel.id = it.id
                findNavController().navigate(parcel)
            }
            adapter.submitList(results)
            rvMoviePopular.adapter = adapter
        }
    }

    private fun setDataUpComingAdapter(results: List<ResultListPlaying>?) {
        getViewBinding().apply {
            val adapter = ListPlayingMoviesAdapter {
                val parcel = HomeFragmentDirections.actionHomeFragmentToDetailMoviesFragment()
                parcel.id = it.id
                findNavController().navigate(parcel)
            }
            adapter.submitList(results)
            rvMovieUpComing.adapter = adapter
        }
    }

    private fun setDataTopRatedAdapter(results: List<ResultListPlaying>?) {
        getViewBinding().apply {
            val adapter = ListPlayingMoviesAdapter {
                val parcel = HomeFragmentDirections.actionHomeFragmentToDetailMoviesFragment()
                parcel.id = it.id
                findNavController().navigate(parcel)
            }
            adapter.submitList(results)
            rvMovieTopRated.adapter = adapter
        }
    }

}