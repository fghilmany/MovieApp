package com.example.movieapplocalstorage.presenter

import android.media.tv.TvView
import com.example.movieapplocalstorage.api.TmdbApiService
import com.example.movieapplocalstorage.model.tvshow.Result
import com.example.movieapplocalstorage.model.tvshow.TvShow
import com.example.movieapplocalstorage.view.MainView
import com.example.movieapplocalstorage.view.TvShowView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowPresenter (
    private val mainView: TvShowView
){

    fun getTvShowList(){
        mainView.showLoading()
        TmdbApiService.create()
            .getTvShow()
            .enqueue(object : Callback<TvShow> {
                override fun onFailure(call: Call<TvShow>, t: Throwable) {

                }

                override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                    mainView.hideLoading()
                    mainView.showMovieList(response.body()?.results as List<Result>)

                }

            })
    }
}