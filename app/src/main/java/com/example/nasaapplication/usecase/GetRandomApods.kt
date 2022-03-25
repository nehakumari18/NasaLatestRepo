package com.example.nasaapplication.usecase

import com.example.nasaapplication.utils.Resource
import com.example.nasaapplication.model.Apod
import com.example.nasaapplication.repository.ApodRepository
import retrofit2.HttpException
import java.io.IOException

class GetRandomApods(
        private val repository: ApodRepository
) {

    suspend operator fun invoke(): Resource<List<Apod>> {
        return try {
            val remoteData = repository.getRandomApods().map { it.toApod() }
            Resource.Success(data = remoteData)
        } catch (e: HttpException) {
            Resource.Error(message = e.message ?: "An error occurred")
        } catch (e: IOException) {
            Resource.Error(message = e.message ?: "An error occurred")
        }
    }
}