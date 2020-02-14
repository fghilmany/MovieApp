package com.example.movieapplocalstorage.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movieapplocalstorage.fragment.favorite.FavoriteMovieFragment
import com.example.movieapplocalstorage.fragment.favorite.FavoriteTvFragment

class FavoriteViewpagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages= listOf(
        FavoriteMovieFragment(),
        FavoriteTvFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
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