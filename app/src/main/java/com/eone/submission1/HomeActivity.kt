package com.eone.submission1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eone.submission1.databinding.ActivityHomeBinding
import com.google.android.material.appbar.CollapsingToolbarLayout

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.collapsingToolbar.title = "XXI"
    }
}