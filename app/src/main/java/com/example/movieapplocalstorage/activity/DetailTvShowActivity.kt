package com.example.movieapplocalstorage.activity

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.movieapplocalstorage.R
import com.example.movieapplocalstorage.db.database
import com.example.movieapplocalstorage.model.detail.tvshow.DetailTvShow
import com.example.movieapplocalstorage.model.favorite.TvFavorite
import com.example.movieapplocalstorage.presenter.DetailTvPresenter
import com.example.movieapplocalstorage.utils.invisible
import com.example.movieapplocalstorage.utils.visible
import com.example.movieapplocalstorage.view.DetailTvShowView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail_tv_show.*
import kotlinx.android.synthetic.main.activity_detail_tv_show.btn_add_to_favorite
import kotlinx.android.synthetic.main.activity_detail_tv_show.iv_detailitem
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_desc_detail
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_popularity
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_rating
import kotlinx.android.synthetic.main.activity_detail_tv_show.tv_title_detailIteam
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTvShowActivity : AppCompatActivity(), DetailTvShowView {

    private lateinit var presenter: DetailTvPresenter
    private lateinit var movie: DetailTvShow
    private var idMovie: Int = 0
    private var isFavorite = false

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)


        idMovie = intent.getIntExtra(EXTRA_ID,0)
        Log.e("cek get","$idMovie")
        presenter = DetailTvPresenter(this)
        presenter.getMovieList(idMovie.toString())
    }

    override fun showLoading() {

        progress_bar_detail_tv.visible()

    }

    override fun hideLoading() {

        progress_bar_detail_tv.invisible()

    }

    override fun showDetailTvShow(data: DetailTvShow) {

        idMovie = intent.getIntExtra(EXTRA_ID,0)

        setFavorite()
        favoriteState()

        movie = DetailTvShow(
            data.backdropPath,
            data.name,
            data.overview,
            data.popularity,
            data.voteAverage,
            data.posterPath
        )
        Picasso.get().load("https://image.tmdb.org/t/p/original/"+movie.backdropPath).centerCrop().fit().into(iv_detailitem)
        tv_title_detailIteam.text = movie.name
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
            val result = select(TvFavorite.TABLE_FAVORITE_TV)
                .whereArgs("(ID_TV = {id})",
                    "id" to idMovie)
            val favorite = result.parseList(classParser<TvFavorite>())
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
                insert(TvFavorite.TABLE_FAVORITE_TV,
                    TvFavorite.ID_TV to idMovie,
                    TvFavorite.TITLE to movie.name,
                    TvFavorite.OVERVIEW to movie.overview,
                    TvFavorite.POSTER_PATH to movie.posterPath)
            }
            Toast.makeText(this,getString(R.string.success_add_favorite), Toast.LENGTH_SHORT).show()

        }catch (e : SQLiteConstraintException){
            Toast.makeText(this,e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(TvFavorite.TABLE_FAVORITE_TV, "(ID_TV = {id})",
                    "id" to idMovie)
            }
            Log.e("cek id remove",idMovie.toString())
            Toast.makeText(this,getString(R.string.success_remove_favorite), Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
            Toast.makeText(this,e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }
}
