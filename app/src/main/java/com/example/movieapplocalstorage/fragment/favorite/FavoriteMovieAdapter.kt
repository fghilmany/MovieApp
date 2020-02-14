package com.example.movieapplocalstorage.fragment.favorite

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplocalstorage.R
import com.example.movieapplocalstorage.model.favorite.MovieFavorite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class FavoriteMovieAdapter(private val favorite:List<MovieFavorite>, val listener:(MovieFavorite)->(Unit))
    : RecyclerView.Adapter<FavoriteViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return FavoriteViewHolder(view)

    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favorite[position],listener)

    }

}

class FavoriteViewHolder (itemView : View):RecyclerView.ViewHolder(itemView) {
    fun bind(result: MovieFavorite,listener: (MovieFavorite) -> Unit) {
        with(itemView){
            tv_title_item.text = result.title
            tv_item_overview.text = result.overview
            Log.e("cek title",result.title)
            Log.e("cek overview",result.overview)
            Log.e("cek poster",result.posterPath)
            Log.e("cek id",result.id.toString())
            Log.e("cek id movie",result.idMovie.toString())
            Picasso.get().load("https://image.tmdb.org/t/p/w185/"+result.posterPath).centerCrop().fit().into(iv_poster_item)

            itemView.btn_detail_item.setOnClickListener {
                listener(result)
            }

        }
    }

}
