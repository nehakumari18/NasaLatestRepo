package com.example.nasaapplication.usecase

import com.example.nasaapplication.model.Apod
import com.example.nasaapplication.repository.ApodRepository

class InsertApod(
        private val repository: ApodRepository
) {

    suspend operator fun invoke(apod: Apod) {
        repository.insertApod(apod.toApodEntity())
    }
}