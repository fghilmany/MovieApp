package com.example.movieapplocalstorage.view

import com.example.movieapplocalstorage.model.tvshow.Result


interface TvShowView {
    fun showLoading()
    fun hideLoading()
    fun showMovieList(data:List<Result>)
}