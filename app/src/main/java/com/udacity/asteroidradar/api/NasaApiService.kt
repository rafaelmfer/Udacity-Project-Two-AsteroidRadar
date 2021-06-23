package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.BuildConfig
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String = getNextSevenDaysFormattedDates().first(),
        @Query("end_date") endDate: String = getNextSevenDaysFormattedDates().last(),
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): String

    @GET("planetary/apod")
    suspend fun getImageOfDay(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : PictureOfDay
}

object NasaApi {
    val retrofitServiceScalars: NasaApiService by lazy { createHttpClientScalars(Constants.BASE_URL, NasaApiService::class) }
    val retrofitServiceMoshi: NasaApiService by lazy { createHttpClientMoshi(Constants.BASE_URL, NasaApiService::class) }
}