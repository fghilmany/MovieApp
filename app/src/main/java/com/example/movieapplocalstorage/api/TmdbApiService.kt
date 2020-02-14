package com.example.movieapplocalstorage.api

import com.example.movieapplocalstorage.BuildConfig
import com.example.movieapplocalstorage.model.detail.movie.Detail
import com.example.movieapplocalstorage.model.detail.tvshow.DetailTvShow
import com.example.movieapplocalstorage.model.movie.TMDBApi
import com.example.movieapplocalstorage.model.tvshow.TvShow
import com.example.movieapplocalstorage.view.DetailTvShowView
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("movie/now_playing")
    fun getMovie(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<TMDBApi>

    @GET("tv/airing_today")
    fun getTvShow(
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<TvShow>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id")movieId:String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<Detail>


    companion object {
        fun create(): TmdbApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()

            return retrofit.create(TmdbApiService::class.java)
        }
    }

    @GET("tv/{tv_id}")
    fun getTvDetail(
        @Path("tv_id")tvId:String,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<DetailTvShow>

}

