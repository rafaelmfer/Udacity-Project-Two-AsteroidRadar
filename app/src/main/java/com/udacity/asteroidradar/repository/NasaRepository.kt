package com.udacity.asteroidradar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.NasaApi
import com.udacity.asteroidradar.api.asDatabaseModel
import com.udacity.asteroidradar.api.getNextSevenDaysFormattedDates
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException

class NasaRepository(private val database: AsteroidsDatabase) {

    val pictureOfDay =  MutableLiveData<PictureOfDay>()

    suspend fun getAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val asteroidsFromApi = parseAsteroidsJsonResult(JSONObject(NasaApi.retrofitServiceScalars.getAsteroids()))
                database.asteroidDatabaseDao.insertAll(*asteroidsFromApi.asDatabaseModel())
            } catch (exception: HttpException) {

            } catch (exception: JSONException) {

            }
        }
    }

    suspend fun getPictureOfDay() {
        withContext(Dispatchers.IO) {
            val pictureOfDayFromApi = NasaApi.retrofitServiceMoshi.getImageOfDay()
            pictureOfDay.postValue(pictureOfDayFromApi)
        }
    }

    fun getAsteroidsStartingToday(): LiveData<List<Asteroid>> {
        return Transformations.map(database.asteroidDatabaseDao.getAllAsteroids(getNextSevenDaysFormattedDates().first())) {
            it.asDomainModel()
        }
    }

    fun getAllTodayAsteroids(): LiveData<List<Asteroid>> {
        return Transformations.map(database.asteroidDatabaseDao.getAllTodayAsteroids(getNextSevenDaysFormattedDates().first())) {
            it.asDomainModel()
        }
    }
}