package com.example.nasaapplication.data.remote

import com.example.nasaapplication.model.Apod
import com.google.gson.annotations.SerializedName

data class ApodDto(
        val copyright: String,
        val date: String,
        val explanation: String,
        val hdurl: String?,
        @SerializedName("media_type")
        val mediaType: String,
        @SerializedName("service_version")
        val serviceVersion: String,
        val title: String,
        val url: String?
) {
    fun toApod(): Apod {
        return Apod(
                date = date,
                explanation = explanation,
                hdurl = hdurl,
                title = title,
                url = url
        )
    }
}