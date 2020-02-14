package com.example.movieapplocalstorage.presenter

import com.example.movieapplocalstorage.api.TmdbApiService
import com.example.movieapplocalstorage.model.detail.tvshow.DetailTvShow
import com.example.movieapplocalstorage.view.DetailTvShowView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTvPresenter (
    private val detailView:DetailTvShowView
){


    fun getMovieList(idTvShow:String) {
        detailView.showLoading()
        TmdbApiService.create()
            .getTvDetail(idTvShow)
            .enqueue(object : Callback<DetailTvShow> {
                override fun onFailure(call: Call<DetailTvShow>, t: Throwable) {

                }

                override fun onResponse(call: Call<DetailTvShow>, response: Response<DetailTvShow>) {
                    detailView.hideLoading()
                    detailView.showDetailTvShow(response.body() as DetailTvShow)
                }

            })
    }
}