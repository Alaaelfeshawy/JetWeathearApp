package com.example.jetweatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings_tbl")
data class UnitClass(
    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit: String)
