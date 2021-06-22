package com.udacity.asteroidradar.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.repository.NasaRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AsteroidsDatabase.getInstance(application.applicationContext)
    private val nasaRepository = NasaRepository(database)

    val asteroids: LiveData<List<Asteroid>> = nasaRepository.asteroids
    val pictureOfDay: LiveData<PictureOfDay> = nasaRepository.pictureOfDay

    private val _navigateToDetailFragment = MutableLiveData<Asteroid>()
    val navigateToDetailFragment get() = _navigateToDetailFragment

    init {
        viewModelScope.launch {
            nasaRepository.getAsteroids()
            nasaRepository.getPictureOfDay()
        }
    }

    fun onAsteroidClicked(asteroid: Asteroid) {
        _navigateToDetailFragment.value = asteroid
    }

    fun onDetailFragmentNavigated() {
        _navigateToDetailFragment.value = null
    }
}