package com.example.nasaapplication.usecase

import com.example.nasaapplication.data.local.ApodEntity
import com.example.nasaapplication.repository.ApodRepository

class DeleteApod(
        private val repository: ApodRepository
) {

    suspend operator fun invoke(apod: ApodEntity) {
        repository.deleteApod(apod)
    }
}