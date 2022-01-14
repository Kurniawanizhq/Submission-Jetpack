package com.eone.submission3.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eone.submission3.ui.home.fragment.content.MovieFragment
import com.eone.submission3.ui.home.fragment.content.TvShowFragment

class HomePagerAdapter(private val fragmentList : List<Any>,fm : FragmentManager,lifecycle : Lifecycle) : FragmentStateAdapter(fm,lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = fragmentList[position] as Fragment

//    override fun createFragment(position: Int): Fragment {
//        var fragment: Fragment? = null
//        when (position) {
//            0 -> fragment = MovieFragment()
//            1 -> fragment = TvShowFragment()
//        }
//        return fragment as Fragment
//    }
}