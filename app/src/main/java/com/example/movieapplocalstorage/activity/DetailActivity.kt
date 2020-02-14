package com.example.movieapplocalstorage.activity

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.movieapplocalstorage.R
import com.example.movieapplocalstorage.db.database
import com.example.movieapplocalstorage.model.detail.movie.Detail
import com.example.movieapplocalstorage.model.favorite.MovieFavorite
import com.example.movieapplocalstorage.presenter.DetailPresenter
import com.example.movieapplocalstorage.utils.invisible
import com.example.movieapplocalstorage.utils.visible
import com.example.movieapplocalstorage.view.DetailView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var presenter: DetailPresenter
    private lateinit var movie: Detail

    private var idMovie : Int = 0
    private var isFavorite = false

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        idMovie = intent.getIntExtra(EXTRA_ID,0)
        Log.e("cek get","$idMovie")
        presenter = DetailPresenter(this)
        presenter.getMovieList(idMovie.toString())



    }

    override fun showLoading() {

        progress_bar_detail_movie.visible()

    }

    override fun hideLoading() {

        progress_bar_detail_movie.invisible()

    }

    override fun showDetailMovie(data: Detail) {

        idMovie = intent.getIntExtra(EXTRA_ID,0)

        setFavorite()
        favoriteState()

        movie = Detail(
            data.backdropPath,
            data.overview,
            data.popularity,
            data.title,
            data.voteAverage,
            data.posterPath
        )
        Picasso.get().load("https://image.tmdb.org/t/p/original/"+movie.backdropPath).centerCrop().fit().into(iv_detailitem)
        tv_title_detailIteam.text = movie.title
        tv_desc_detail.text = movie.overview
        tv_popularity.text = movie.popularity.toString()
        tv_rating.text = movie.voteAverage.toString()

        btn_add_to_favorite.setOnClickListener {
            if (isFavorite)
                removeFromFavorite()
            else
                addToFavorite()

            isFavorite != isFavorite
            setFavorite()
        }

    }

    private fun favoriteState() {
        database.use {
            val result = select(MovieFavorite.TABLE_FAVORITE)
                .whereArgs("(ID_MOVIE = {id})",
                    "id" to idMovie)
            val favorite = result.parseList(classParser<MovieFavorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            btn_add_to_favorite.text = getString(R.string.text_delete_favorite)
        else
            btn_add_to_favorite.text = getString(R.string.text_add_favorite)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(MovieFavorite.TABLE_FAVORITE,
                    MovieFavorite.ID_MOVIE to idMovie,
                    MovieFavorite.TITLE to movie.title,
                    MovieFavorite.OVERVIEW to movie.overview,
                    MovieFavorite.POSTER_PATH to movie.posterPath)
            }
            Toast.makeText(this,getString(R.string.success_add_favorite), Toast.LENGTH_SHORT).show()

        }catch (e : SQLiteConstraintException){
            Toast.makeText(this,e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(MovieFavorite.TABLE_FAVORITE, "(ID_MOVIE = {id})",
                    "id" to idMovie)
            }
            Log.e("cek id remove",idMovie.toString())
            Toast.makeText(this,getString(R.string.success_remove_favorite),Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }
}
