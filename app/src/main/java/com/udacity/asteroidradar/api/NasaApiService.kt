package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String = "2015-09-07",
        @Query("end_date") endDate: String = "2015-09-08",
        @Query("api_key") apiKey: String = ""
    ): Deferred<String>

    @GET("planetary/apod")
    suspend fun getImageOfDay(
        @Query("api_key") apiKey: String = ""
    )
}

object NasaApi {
    val retrofitServiceScalars: NasaApiService by lazy { createHttpClientScalars(Constants.BASE_URL, NasaApiService::class) }
    val retrofitServiceMoshi: NasaApiService by lazy { createHttpClientMoshi(Constants.BASE_URL, NasaApiService::class) }
}