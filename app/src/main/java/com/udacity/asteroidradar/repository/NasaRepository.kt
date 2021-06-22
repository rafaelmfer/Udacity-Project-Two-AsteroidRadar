package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.asDatabaseModel
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class NasaRepository(private val database: AsteroidsDatabase) {

    val asteroids: LiveData<List<Asteroid>> = Transformations.map(database.asteroidDatabaseDao.getAllAsteroids()) {
        it.asDomainModel()
    }

    val pictureOfDay =  MutableLiveData<PictureOfDay>()

    suspend fun getAsteroids() {
        withContext(Dispatchers.IO) {
            val asteroidsFromApi = parseAsteroidsJsonResult(JSONObject(NasaApi.retrofitServiceScalars.getAsteroids()))
            database.asteroidDatabaseDao.insertAll(*asteroidsFromApi.asDatabaseModel())
        }
    }

    suspend fun getPictureOfDay() {
        withContext(Dispatchers.IO) {
            val pictureOfDayFromApi = NasaApi.retrofitServiceMoshi.getImageOfDay()
            pictureOfDay.postValue(pictureOfDayFromApi)
        }
    }
}