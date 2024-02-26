package com.example.jetweatherapp.repository

import com.example.jetweatherapp.data.WeatherDao
import com.example.jetweatherapp.model.Favorite
import com.example.jetweatherapp.model.UnitClass
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    suspend fun getFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()
    suspend fun insertFavorite(favorite: Favorite) = weatherDao.insertFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = weatherDao.updateFavorite(favorite)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavorites()
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)
    suspend fun getFavById(city: String): Favorite ?= weatherDao.getFavById(city)

    fun getUnits(): Flow<List<UnitClass>> = weatherDao.getUnitsList()
    suspend fun insertUnit(unit: UnitClass) = weatherDao.insertUnit(unit)
    suspend fun updateUnit(unit: UnitClass) = weatherDao.updateUnit(unit)
    suspend fun deleteAllUnits() = weatherDao.deleteAllUnits()
    suspend fun deleteUnit(unit: UnitClass) = weatherDao.deleteUnit(unit)



}