package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.AsteroidsDatabase
import com.udacity.asteroidradar.repository.NasaRepository
import retrofit2.HttpException

class RefreshAsteroidsDataWorker(appContext: Context, params: WorkerParameters) : CoroutineWorker(appContext, params) {

    companion object {
        const val ASTEROID_REFRESH_WORK = "RefreshAsteroidsDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = AsteroidsDatabase.getInstance(applicationContext)
        val nasaRepository = NasaRepository(database)

        return try {
            nasaRepository.getAsteroids()
            Result.success()
        } catch (e: HttpException) {
            e.printStackTrace()
            Result.retry()
        }
    }
}