package com.example.nasaapplication.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapplication.data.local.ApodEntity
import com.example.nasaapplication.usecase.ApodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
        private val useCases: ApodUseCases
) : ViewModel() {

    val savedApods = useCases.getAllApods()

    fun deleteApod(apod: ApodEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.deleteApod(apod)
        }
    }
}