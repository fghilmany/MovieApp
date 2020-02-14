package com.example.movieapplocalstorage.view

import com.example.movieapplocalstorage.model.detail.movie.Detail
import com.example.movieapplocalstorage.model.favorite.MovieFavorite

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMovie(data: Detail)
}