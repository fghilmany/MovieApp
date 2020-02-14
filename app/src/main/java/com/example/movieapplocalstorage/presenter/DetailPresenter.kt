package com.example.movieapplocalstorage.presenter

import com.example.movieapplocalstorage.api.TmdbApiService
import com.example.movieapplocalstorage.model.detail.movie.Detail
import com.example.movieapplocalstorage.view.DetailView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter (
    private val detailView:DetailView
){


    fun getMovieList(idMovie:String) {
        detailView.showLoading()
        TmdbApiService.create()
            .getDetailMovie(idMovie)
            .enqueue(object : Callback<Detail>{
                override fun onFailure(call: Call<Detail>, t: Throwable) {

                }

                override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                    detailView.hideLoading()
                    detailView.showDetailMovie(response.body() as Detail)
                }

            })
    }
}