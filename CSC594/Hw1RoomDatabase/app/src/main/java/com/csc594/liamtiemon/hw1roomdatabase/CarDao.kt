package com.csc594.liamtiemon.hw1roomdatabase

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CarDao {

    @Query("SELECT * from car_table ORDER BY car ASC")
    fun getAllCars(): LiveData<List<Car>>

    @Insert
    fun insert(car: Car)

    @Query("DELETE FROM car_table")
    fun deleteAll()
}