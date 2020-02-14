package com.example.movieapplocalstorage.fragment.favorite


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapplocalstorage.R
import com.example.movieapplocalstorage.activity.DetailActivity
import com.example.movieapplocalstorage.db.database
import com.example.movieapplocalstorage.model.favorite.MovieFavorite
import com.example.movieapplocalstorage.utils.invisible
import com.example.movieapplocalstorage.utils.visible
import com.example.movieapplocalstorage.view.FavoriteMovieView
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select


class FavoriteMovieFragment : Fragment(), FavoriteMovieView {

    private var movies : MutableList<MovieFavorite> = mutableListOf()
    private lateinit var adapter: FavoriteMovieAdapter
    private lateinit var movie: MovieFavorite


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showFavorite()

        adapter = FavoriteMovieAdapter(movies){
            val i = Intent(activity, DetailActivity::class.java)
            i.putExtra(DetailActivity.EXTRA_ID, it.idMovie)
            Log.e("cek intent id",it.id.toString())
            startActivity(i)
        }

        rv_favorite_movie.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        rv_favorite_movie.adapter = adapter

    }

    private fun showFavorite() {
        movies.clear()
        context?.database?.use {
            val result = select(MovieFavorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<MovieFavorite>())
            movies.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun showLoading() {

        progress_bar_movie.visible()

    }

    override fun hideLoading() {

        progress_bar_movie.invisible()

    }



}
