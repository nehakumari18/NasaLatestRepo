package com.example.nasaapplication.usecase

import androidx.lifecycle.LiveData
import com.example.nasaapplication.data.local.ApodEntity
import com.example.nasaapplication.repository.ApodRepository

class GetAllApods(
        private val repository: ApodRepository
) {

    operator fun invoke(): LiveData<List<ApodEntity>> {

        return repository.getAll()
    }
}