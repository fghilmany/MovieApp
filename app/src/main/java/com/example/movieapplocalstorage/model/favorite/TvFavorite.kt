package com.example.movieapplocalstorage.model.favorite


data class TvFavorite(
    val id: Long?,
    val idTv : Int?,
    val overview : String?,
    val posterPath : String?,
    val title : String?){

    companion object{
        const val TABLE_FAVORITE_TV: String = "TABLE_FAVORITE_TV"
        const val ID: String = "ID_"
        const val ID_TV: String = "ID_TV"
        const val TITLE: String = "TITLE"
        const val OVERVIEW: String = "OVERVIEW"
        const val POSTER_PATH: String = "POSTER_PATH"
    }
}
