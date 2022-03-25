package com.example.nasaapplication.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.nasaapplication.BaseFragment

import com.example.nasaapplication.databinding.FragmentApodFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment : BaseFragment<FragmentApodFavoritesBinding>() {

    private val viewModel: FavoritesViewModel by viewModels()

    private lateinit var adapter: FavoritesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FavoritesAdapter(
                onDelete = { apod ->
                    viewModel.deleteApod(apod)
                }
        )

        viewModel.savedApods.observe(this.viewLifecycleOwner) { apods ->
            apods.let {
                adapter.submitList(it)
            }
        }
        binding.rvFavoriteApods.adapter = adapter

    }

    override fun initBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentApodFavoritesBinding.inflate(inflater, container, false)
}