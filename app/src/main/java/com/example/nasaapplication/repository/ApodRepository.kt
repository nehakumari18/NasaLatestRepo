package com.example.nasaapplication.repository

import androidx.lifecycle.LiveData
import com.example.nasaapplication.data.local.ApodEntity
import com.example.nasaapplication.data.remote.ApodDto

interface ApodRepository {

    suspend fun getTodayApod(): ApodDto

    suspend fun getRandomApods(): List<ApodDto>

    suspend fun insertApods(apods: List<ApodEntity>)

    fun getAll(): LiveData<List<ApodEntity>>

    suspend fun deleteApod(apod: ApodEntity)

    suspend fun insertApod(apod: ApodEntity)
}