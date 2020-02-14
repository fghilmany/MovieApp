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
import com.example.movieapplocalstorage.activity.DetailTvShowActivity
import com.example.movieapplocalstorage.model.tvshow.Result
import com.example.movieapplocalstorage.presenter.TvShowPresenter
import com.example.movieapplocalstorage.utils.invisible
import com.example.movieapplocalstorage.utils.visible
import com.example.movieapplocalstorage.view.TvShowView
import kotlinx.android.synthetic.main.fragment_tv_list.*

/**
 * A simple [Fragment] subclass.
 */
class TvListFragment : Fragment(), TvShowView {

    private var tvShow : MutableList<Result> = mutableListOf()
    private lateinit var adapter: TvAdapter
    private lateinit var presenter: TvShowPresenter
    private lateinit var tv:Result

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        presenter = TvShowPresenter(this)
        presenter.getTvShowList()
        adapter = TvAdapter(tvShow){
            val i = Intent(activity, DetailTvShowActivity::class.java)
            i.putExtra(DetailTvShowActivity.EXTRA_ID, it.id)
            Log.e("cek intent id",it.id.toString())
            startActivity(i)
        }

        rv_tv_show_list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        rv_tv_show_list.adapter = adapter

    }

    override fun showLoading() {
        progress_bar_tv.visible()

    }

    override fun hideLoading() {
        progress_bar_tv.invisible()

    }

    override fun showMovieList(data: List<Result>) {
        tvShow.clear()
        tvShow.addAll(data)
        adapter.notifyDataSetChanged()

        tv = Result(
            data[0].id,
            data[0].name,
            data[0].overview,
            data[0].posterPath
        )

        Log.e("cek id data",data[0].id.toString())

    }


}
