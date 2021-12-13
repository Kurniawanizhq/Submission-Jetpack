package com.eone.submission1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eone.submission1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}