package com.example.nasaapplication.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.nasaapplication.BaseFragment
import com.example.nasaapplication.databinding.FragmentApodRandomBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class RandomFragment : BaseFragment<FragmentApodRandomBinding>() {

    private val viewModel: RandomViewModel by viewModels()

    private lateinit var adapter: RandomAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RandomAdapter(
                onAdd = { apod ->
                    viewModel.insertApods(apod)
                }
        )

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {

                    is RandomEvent.Failure -> {
                        binding.apply {
                            pbLoadingItems.isVisible = false
                            rvApodItems.isVisible = false
                            imgNoInternetRandomApod.isVisible = true
                        }
                        Toast.makeText(activity, event.errorText, Toast.LENGTH_SHORT).show()
                    }

                    is RandomEvent.Success -> {
                        binding.apply {
                            pbLoadingItems.isVisible = false
                            rvApodItems.isVisible = true
                        }
                        adapter.submitList(event.apods)
                    }

                    is RandomEvent.Loading -> {
                        binding.apply {
                            pbLoadingItems.isVisible = true
                            rvApodItems.isVisible = false
                        }

                    }

                    else -> Unit
                }
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRandomApods()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        binding.rvApodItems.adapter = adapter

    }

    override fun initBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentApodRandomBinding.inflate(inflater, container, false)
}