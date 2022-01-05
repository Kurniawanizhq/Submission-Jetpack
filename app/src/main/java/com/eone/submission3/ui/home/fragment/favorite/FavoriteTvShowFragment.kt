package com.eone.submission3.ui.home.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.eone.submission3.databinding.FragmentFavoriteTvShowBinding

class FavoriteTvShowFragment : Fragment() {

    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}