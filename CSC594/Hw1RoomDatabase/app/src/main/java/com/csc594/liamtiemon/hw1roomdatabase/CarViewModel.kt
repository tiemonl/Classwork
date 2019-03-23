package com.csc594.liamtiemon.hw1roomdatabase

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

class CarViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: CarRepository
    val allCars: LiveData<List<Car>>

    init {
        val carsDao = CarRoomDatabase.getDatabase(application, scope).carDao()
        repository = CarRepository(carsDao)
        allCars = repository.allCars
    }

    fun insert(car: Car) = scope.launch(Dispatchers.IO) {
        repository.insert(car)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}