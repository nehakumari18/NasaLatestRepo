package com.example.nasaapplication.todayScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapplication.utils.Resource
import com.example.nasaapplication.model.Apod
import com.example.nasaapplication.usecase.ApodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(
        private val useCases: ApodUseCases
): ViewModel() {

    private val _uiEvent = MutableStateFlow<TodayEvent>(TodayEvent.Empty)
    val uiEvent = _uiEvent.asStateFlow()

    init {
        getTodayApod()
    }

    private fun getTodayApod() {
        viewModelScope.launch() {
            _uiEvent.value = TodayEvent.Loading
            when (val apodResponse = useCases.getTodayApod()) {
                is Resource.Error -> _uiEvent.value = TodayEvent.Failure(apodResponse.message!!)
                is Resource.Success -> _uiEvent.value = TodayEvent.Success(apodResponse.data!!)
                else -> Unit
            }
        }
    }

    fun insertApods(apod: Apod) {
        viewModelScope.launch {
            useCases.insertApod(apod)
        }
    }

}