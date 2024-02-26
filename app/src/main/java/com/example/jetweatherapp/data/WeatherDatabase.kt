package com.example.jetweatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetweatherapp.model.Favorite
import com.example.jetweatherapp.model.UnitClass

@Database(entities = [Favorite::class , UnitClass::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}