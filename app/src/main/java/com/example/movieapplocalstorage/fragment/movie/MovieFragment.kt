package com.example.movieapplocalstorage.fragment.movie


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
import com.example.movieapplocalstorage.model.movie.Result
import com.example.movieapplocalstorage.presenter.MoviePresenter
import com.example.movieapplocalstorage.utils.invisible
import com.example.movieapplocalstorage.utils.visible
import com.example.movieapplocalstorage.view.MainView
import kotlinx.android.synthetic.main.fragment_movie.*


class MovieFragment : Fragment(), MainView {

    private var movies : MutableList<Result> = mutableListOf()
    private lateinit var adapter: MovieAdapter
    private lateinit var presenter: MoviePresenter
    private lateinit var movie: Result

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MoviePresenter(this)
        presenter.getMovieList()
        adapter = MovieAdapter(movies){
            val i = Intent(activity,DetailActivity::class.java)
            i.putExtra(DetailActivity.EXTRA_ID, it.id)
            Log.e("cek intent id",movie.id.toString())
            startActivity(i)
        }
        rv_movie_list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        rv_movie_list.adapter = adapter
    }

    override fun showLoading() {

        progress_bar_movie.visible()

    }

    override fun hideLoading() {

        progress_bar_movie.invisible()

    }

    override fun showMovieList(data: List<Result>) {

        movies.clear()
        movies.addAll(data)
        adapter.notifyDataSetChanged()

        movie = Result(
            data[0].posterPath,
            data[0].title,
            data[0].overview,
            data[0].id
        )

    }


}
