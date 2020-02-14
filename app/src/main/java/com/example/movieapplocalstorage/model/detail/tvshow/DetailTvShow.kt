package com.example.movieapplocalstorage.model.detail.tvshow


import com.google.gson.annotations.SerializedName

data class DetailTvShow(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("poster_path")
    val posterPath:String
)