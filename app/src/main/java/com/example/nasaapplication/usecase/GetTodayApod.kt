package com.example.nasaapplication.usecase

import com.example.nasaapplication.utils.Resource
import com.example.nasaapplication.model.Apod
import com.example.nasaapplication.repository.ApodRepository
import retrofit2.HttpException
import java.io.IOException

class GetTodayApod(
        private val repository: ApodRepository
) {

    suspend operator fun invoke(): Resource<Apod> {
        return try {
            val result = repository.getTodayApod().toApod()
            Resource.Success(data = result)
        } catch (e: HttpException) {
            Resource.Error(message = e.message ?: "An error occurred")
        } catch (e: IOException) {
            Resource.Error(message = e.message ?: "An error occurred")
        }
    }
}