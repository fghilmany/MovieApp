package com.example.movieapplocalstorage.view

import com.example.movieapplocalstorage.model.movie.Result

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMovieList(data:List<Result>)
}