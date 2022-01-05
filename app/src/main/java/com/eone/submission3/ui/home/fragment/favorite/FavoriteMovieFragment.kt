package com.eone.submission3.ui.home.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eone.submission3.databinding.FragmentFavoriteMovieBinding

class FavoriteMovieFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteMovieBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}