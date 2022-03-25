package com.example.nasaapplication.usecase

data class ApodUseCases(
        val getTodayApod: GetTodayApod,
        val getRandomApods: GetRandomApods,
        val insertApod: InsertApod,
        val getAllApods: GetAllApods,
        val deleteApod: DeleteApod
)