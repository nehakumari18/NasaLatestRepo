package com.example.nasaapplication.data

import androidx.lifecycle.LiveData
import com.example.nasaapplication.data.local.ApodDao
import com.example.nasaapplication.data.local.ApodEntity
import com.example.nasaapplication.data.remote.ApodApi
import com.example.nasaapplication.data.remote.ApodDto
import com.example.nasaapplication.repository.ApodRepository

class ApodRepositoryImpl(
        private val api: ApodApi,
        private val dao: ApodDao
): ApodRepository {

    override suspend fun getTodayApod(): ApodDto {
        return api.getTodayApod()
    }

    override suspend fun getRandomApods(): List<ApodDto> {
        return api.getRandomApods()
    }

    override suspend fun insertApods(apods: List<ApodEntity>) {
        dao.insertApods(apods)
    }

    override fun getAll(): LiveData<List<ApodEntity>> {
        return dao.getAll()
    }

    override suspend fun deleteApod(apod: ApodEntity) {
        dao.deleteApod(apod)
    }

    override suspend fun insertApod(apod: ApodEntity) {
        dao.insertApod(apod)
    }
}