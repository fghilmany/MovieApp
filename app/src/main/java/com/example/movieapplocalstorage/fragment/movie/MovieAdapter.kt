package com.example.movieapplocalstorage.fragment.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapplocalstorage.R
import com.example.movieapplocalstorage.model.movie.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(var movie:List<Result>, private var listener: (Result)->(Unit))
    :RecyclerView.Adapter<MovieViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false))
    }

    override fun getItemCount(): Int = movie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bindHolder(movie[position], listener)

    }

}

class MovieViewHolder(view: View):RecyclerView.ViewHolder(view) {

    fun bindHolder(item: Result, listener: (Result) -> Unit){
        Picasso.get().load("https://image.tmdb.org/t/p/w185/"+item.posterPath)
            .centerCrop().fit().into(itemView.iv_poster_item)
        itemView.tv_title_item.text = item.title
        itemView.tv_item_overview.text = item.overview

        itemView.btn_detail_item.setOnClickListener {
            listener(item)
        }
    }

}
