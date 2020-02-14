package com.example.movieapplocalstorage.fragment.favorite

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplocalstorage.R
import com.example.movieapplocalstorage.model.favorite.TvFavorite
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class FavoriteTvAdapter(private val favorite:List<TvFavorite>, val listener:(TvFavorite)->(Unit))
    : RecyclerView.Adapter<FavoriteTvViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return FavoriteTvViewHolder(view)

    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteTvViewHolder, position: Int) {
        holder.bind(favorite[position],listener)

    }

}

class FavoriteTvViewHolder (itemView : View): RecyclerView.ViewHolder(itemView) {
    fun bind(result: TvFavorite, listener: (TvFavorite) -> Unit) {
        with(itemView){
            tv_title_item.text = result.title
            tv_item_overview.text = result.overview
            Log.e("cek title",result.title)
            Log.e("cek overview",result.overview)
            Log.e("cek poster",result.posterPath)
            Log.e("cek id",result.id.toString())
            Log.e("cek id movie",result.idTv.toString())
            Picasso.get().load("https://image.tmdb.org/t/p/w185/"+result.posterPath).centerCrop().fit().into(iv_poster_item)

            itemView.btn_detail_item.setOnClickListener {
                listener(result)
            }

        }
    }

}
