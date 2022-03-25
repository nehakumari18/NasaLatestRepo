package com.example.nasaapplication.todayScreen

import com.example.nasaapplication.model.Apod

sealed class TodayEvent {
    class Success(val apod: Apod): TodayEvent()
    class Failure(val errorText: String): TodayEvent()
    object Loading : TodayEvent()
    object Empty : TodayEvent()
}