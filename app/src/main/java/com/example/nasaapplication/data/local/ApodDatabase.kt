package com.example.nasaapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
        entities = [ApodEntity::class],
        version = 1
)
abstract class ApodDatabase: RoomDatabase() {

    abstract val dao: ApodDao
}