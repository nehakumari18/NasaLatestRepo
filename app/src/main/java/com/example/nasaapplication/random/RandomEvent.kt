package com.example.nasaapplication.random

import com.example.nasaapplication.model.Apod

sealed class RandomEvent {
    class Success(val apods: List<Apod>): RandomEvent()
    class Failure(val errorText: String): RandomEvent()
    object Loading : RandomEvent()
    object Empty : RandomEvent()
}