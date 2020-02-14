package com.example.movieapplocalstorage.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movieapplocalstorage.fragment.movie.MovieFragment
import com.example.movieapplocalstorage.fragment.movie.TvListFragment

class HomeViewpagerAdapter (fm:FragmentManager):FragmentPagerAdapter(fm){

    private val pages= listOf(
        MovieFragment(),
        TvListFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "Movie"
            else -> "TV Series"
        }
    }

}