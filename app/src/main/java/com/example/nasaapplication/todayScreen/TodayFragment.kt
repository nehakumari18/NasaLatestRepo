package com.example.nasaapplication.todayScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.nasaapplication.BaseFragment
import com.example.nasaapplication.databinding.FragmentApodTodayBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class TodayFragment : BaseFragment<FragmentApodTodayBinding>() {


    private val viewModel: TodayViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEvent.collect { event ->
                when (event) {

                    is TodayEvent.Success -> {
                        binding.apply {
                            pbLoading.isVisible = false
                            svTodayApod.isVisible = true
                            tvTitle.text = event.apod.title
                            tvDate.text = event.apod.date
                            tvContent.text = event.apod.explanation
                            imgApod.load(event.apod.url)
                            btnAddFavoritesFromToday.setOnClickListener {
                                viewModel.insertApods(event.apod)
                            }
                        }
                    }

                    is TodayEvent.Failure -> {
                        binding.apply {
                            pbLoading.isVisible = false
                            svTodayApod.isVisible = false
                            imgNoInternetTodayApod.isVisible = true
                        }
                        Toast.makeText(activity, event.errorText, Toast.LENGTH_SHORT).show()
                    }

                    is TodayEvent.Loading -> {
                        binding.pbLoading.isVisible = true
                        binding.svTodayApod.isVisible = false
                    }

                    else -> Unit
                }
            }
        }

    }

    override fun initBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
    ) = FragmentApodTodayBinding.inflate(inflater, container, false)
}