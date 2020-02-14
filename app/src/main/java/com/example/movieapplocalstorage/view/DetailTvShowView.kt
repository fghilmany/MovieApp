package com.example.movieapplocalstorage.view

import com.example.movieapplocalstorage.model.detail.tvshow.DetailTvShow

interface DetailTvShowView {
    fun showLoading()
    fun hideLoading()
    fun showDetailTvShow(data: DetailTvShow)
}