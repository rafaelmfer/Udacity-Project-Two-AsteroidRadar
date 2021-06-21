package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.repository.NasaRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AsteroidsDatabase.getInstance(application.applicationContext)
    private val nasaRepository = NasaRepository(database)

    val asteroids: LiveData<List<Asteroid>> = nasaRepository.asteroids

    init {
        viewModelScope.launch {
            nasaRepository.getAsteroids()
        }
    }
}