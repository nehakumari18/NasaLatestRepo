package com.example.nasaapplication

import android.app.Application
import androidx.room.Room
import com.example.nasaapplication.utils.Constants.BASE_URL
import com.example.nasaapplication.data.local.ApodDatabase
import com.example.nasaapplication.data.remote.ApodApi
import com.example.nasaapplication.data.ApodRepositoryImpl

import com.example.nasaapplication.repository.ApodRepository
import com.example.nasaapplication.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApodModule {

    @Provides
    @Singleton
    fun provideApodUseCases(repository: ApodRepository): ApodUseCases {
        return ApodUseCases(
                getTodayApod = GetTodayApod(repository),
                getRandomApods = GetRandomApods(repository),
                insertApod = InsertApod(repository),
                getAllApods = GetAllApods(repository),
                deleteApod = DeleteApod(repository)
        )
    }

    @Provides
    @Singleton
    fun provideApodDatabase(application: Application): ApodDatabase {
        return Room.databaseBuilder(
                application,
                ApodDatabase::class.java,
                "apod_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideApodApi(): ApodApi {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApodApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApodRepository(api: ApodApi, db: ApodDatabase): ApodRepository {
        return ApodRepositoryImpl(api, db.dao)
    }
}