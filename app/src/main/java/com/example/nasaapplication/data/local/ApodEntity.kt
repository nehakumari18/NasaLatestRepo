package com.example.nasaapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nasaapplication.model.Apod


@Entity(tableName = "apod_db")
data class ApodEntity(
        val date: String,
        val explanation: String,
        val hdurl: String?,
        val title: String,
        val url: String?,
        @PrimaryKey val id: Int? = null
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