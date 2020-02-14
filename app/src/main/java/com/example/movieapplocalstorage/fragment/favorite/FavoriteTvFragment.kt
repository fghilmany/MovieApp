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
import com.example.movieapplocalstorage.activity.DetailTvShowActivity
import com.example.movieapplocalstorage.db.database
import com.example.movieapplocalstorage.model.favorite.TvFavorite
import kotlinx.android.synthetic.main.fragment_favorite_tv.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvFragment : Fragment() {

    private var movies : MutableList<TvFavorite> = mutableListOf()
    private lateinit var adapter: FavoriteTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showFavorite()


        adapter = FavoriteTvAdapter(movies){
            val i = Intent(activity, DetailTvShowActivity::class.java)
            i.putExtra(DetailTvShowActivity.EXTRA_ID, it.idTv)
            Log.e("cek intent id",it.id.toString())
            startActivity(i)
        }

        rv_favorite_tv.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        rv_favorite_tv.adapter = adapter

    }

    private fun showFavorite() {
        movies.clear()
        context?.database?.use {
            val result = select(TvFavorite.TABLE_FAVORITE_TV)
            val favorite = result.parseList(classParser<TvFavorite>())
            movies.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }


}
