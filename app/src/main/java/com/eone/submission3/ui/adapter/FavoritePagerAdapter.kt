package com.eone.submission3.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eone.submission3.ui.home.fragment.content.MovieFragment
import com.eone.submission3.ui.home.fragment.content.TvShowFragment
import com.eone.submission3.ui.home.fragment.favorite.FavoriteMovieFragment
import com.eone.submission3.ui.home.fragment.favorite.FavoriteTvShowFragment

class FavoritePagerAdapter(activity : AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTvShowFragment()
        }
        return fragment as Fragment
    }
}