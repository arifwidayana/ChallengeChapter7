package com.arifwidayana.challengechapter7.data.network.service

import com.arifwidayana.challengechapter7.data.network.model.response.details.DetailsMovie
import com.arifwidayana.challengechapter7.data.network.model.response.listmovies.ListPlayingMovie
import com.arifwidayana.challengechapter7.utils.Constant
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface MovieService {
    @GET(Constant.NOW_PLAYING)
    suspend fun getNowPlayingMovie(): ListPlayingMovie

    @GET(Constant.POPULAR)
    suspend fun getPopularMovie(): ListPlayingMovie

    @GET(Constant.UP_COMING)
    suspend fun getUpComingMovie(): ListPlayingMovie

    @GET(Constant.TOP_RATED)
    suspend fun getTopRatedMovie(): ListPlayingMovie

    @GET(Constant.DETAIL_MOVIES)
    suspend fun getDetailMovie(@Path("id") id: Int?): DetailsMovie

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor : ChuckerInterceptor): MovieService{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .addInterceptor {
                    val req = it.request()
                    val query = req.url
                        .newBuilder()
                        .addQueryParameter(Constant.API_NAME, Constant.API_KEY)
                        .build()
                    return@addInterceptor it.proceed(req.newBuilder().url(query).build())
                }
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(MovieService::class.java)
        }
    }
}