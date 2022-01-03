package com.eone.submission3.ui.home.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.eone.submission3.FavoriteFragment
import com.eone.submission3.FragmentHome
import com.eone.submission3.R
import com.eone.submission3.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home ->loadFragment(FragmentHome())
            R.id.nav_favorite -> loadFragment(FavoriteFragment())
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(FragmentHome())
        binding.bottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

   }

    fun setUpNavController(){
      }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fm_container, fragment)
                .commit()
            return true
        }
        return false
    }
}