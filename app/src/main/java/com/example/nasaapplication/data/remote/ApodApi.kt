package com.example.nasaapplication.data.remote

import com.example.nasaapplication.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodApi {
    @GET("planetary/apod?api_key=${API_KEY}")
    suspend fun getTodayApod(): ApodDto

    @GET("planetary/apod")
    suspend fun getRandomApods(
            @Query("count") count: String = "5",
            @Query("api_key") apiKey: String = API_KEY
    ): List<ApodDto>

}