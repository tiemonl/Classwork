package com.csc594.liamtiemon.hw1roomdatabase

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class CarRepository(private val carDao: CarDao) {

    val allCars: LiveData<List<Car>> = carDao.getAllCars()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(car: Car) {
        carDao.insert(car)
    }
}