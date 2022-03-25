package com.example.nasaapplication.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapplication.utils.Resource
import com.example.nasaapplication.model.Apod
import com.example.nasaapplication.usecase.ApodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
        private val apodUseCases: ApodUseCases
): ViewModel() {

    private val _uiEvent = MutableStateFlow<RandomEvent>(RandomEvent.Empty)
    val uiEvent = _uiEvent.asStateFlow()

    fun insertApods(apod: Apod) {
        viewModelScope.launch {
            apodUseCases.insertApod(apod)
        }
    }

    init {
        getRandomApods()
    }

    fun getRandomApods() {
        viewModelScope.launch {
            _uiEvent.value = RandomEvent.Loading
            when (val apodResponse = apodUseCases.getRandomApods()) {
                is Resource.Error -> _uiEvent.value = RandomEvent.Failure(apodResponse.message!!)
                is Resource.Success -> _uiEvent.value = RandomEvent.Success(apodResponse.data!!)
                else -> Unit
            }
        }
    }
}