package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDatabaseDao {

    @Query("SELECT * FROM asteroid_table ORDER BY close_approach_date")
    fun getAllAsteroids(): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: DatabaseAsteroid)

    @Query("SELECT * FROM asteroid_table WHERE id = :idAsteroid")
    fun getAsteroid(idAsteroid: Long): DatabaseAsteroid

    @Query("DELETE FROM asteroid_table")
    fun clear()
}