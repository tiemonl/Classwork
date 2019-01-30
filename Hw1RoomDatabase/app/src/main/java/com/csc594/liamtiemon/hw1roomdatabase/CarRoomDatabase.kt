package com.csc594.liamtiemon.hw1roomdatabase

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

@Database(entities = [Car::class], version = 1)
abstract class CarRoomDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var INSTANCE: CarRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): CarRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CarRoomDatabase::class.java,
                        "car_database"
                )
                        .fallbackToDestructiveMigration()
                        .addCallback(CarDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }

        private class CarDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.carDao())
                    }
                }
            }
        }

        fun populateDatabase(carDao: CarDao) {
            carDao.deleteAll()

            var car = Car("Maserati")
            carDao.insert(car)
            car = Car("Ferrari")
            carDao.insert(car)
        }
    }
}