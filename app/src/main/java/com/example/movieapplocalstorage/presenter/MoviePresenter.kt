package com.example.movieapplocalstorage.presenter

import com.example.movieapplocalstorage.api.TmdbApiService
import com.example.movieapplocalstorage.model.movie.Result
import com.example.movieapplocalstorage.model.movie.TMDBApi
import com.example.movieapplocalstorage.view.MainView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class MoviePresenter (
    private val mainView: MainView
) {

    fun getMovieList() {
        mainView.showLoading()
        TmdbApiService.create()
            .getMovie()
            .enqueue(object : Callback<TMDBApi> {
                override fun onFailure(call: Call<TMDBApi>, t: Throwable) {

                }

                override fun onResponse(call: Call<TMDBApi>, response: Response<TMDBApi>) {

                    mainView.hideLoading()
                    mainView.showMovieList(response.body()?.results as List<Result>)


                }

            })
    }

}