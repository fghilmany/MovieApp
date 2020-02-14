package com.example.movieapplocalstorage.model.favorite

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MovieFavorite(
    val id: Long?,
    val idMovie : Int?,
    val overview : String?,
    val posterPath : String?,
    val title : String?){

    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_MOVIE: String = "ID_MOVIE"
        const val TITLE: String = "TITLE"
        const val OVERVIEW: String = "OVERVIEW"
        const val POSTER_PATH: String = "POSTER_PATH"
    }
}