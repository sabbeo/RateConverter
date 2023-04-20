package com.example.rateconverter.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
class Rate(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "code") val code: String,
    @ColumnInfo(name = "flag") val flag: String = code.lowercase() + ".png"

)
