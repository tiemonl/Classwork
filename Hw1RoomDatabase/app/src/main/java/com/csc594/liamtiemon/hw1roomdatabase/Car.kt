package com.csc594.liamtiemon.hw1roomdatabase

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "car_table")
data class Car(@PrimaryKey @ColumnInfo(name = "car") val car: String)