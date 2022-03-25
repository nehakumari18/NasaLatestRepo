package com.example.nasaapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ApodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApods(apods: List<ApodEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApod(apod: ApodEntity)

    @Delete
    suspend fun deleteApod(apod: ApodEntity)

    @Query("SELECT * FROM apod_db")
    fun getAll(): LiveData<List<ApodEntity>>

    @Query("SELECT * FROM apod_db WHERE id = :id")
    suspend fun getItemById(id: Int): ApodEntity

    @Query("DELETE FROM apod_db")
    suspend fun deleteAll()
}