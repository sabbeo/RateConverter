package com.example.rateconverter.data

import androidx.room.Dao
import androidx.room.*
import com.example.rateconverter.models.Rate

@Dao
interface RateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRate(rate: Rate)

    @Query("SELECT * FROM rates")
    suspend fun getAllRates(): List<Rate>

    @Query("SELECT * FROM rates WHERE id = :id")
    suspend fun getRateById(id: Int): Rate?

    @Query("DELETE FROM rates")
    suspend fun deleteAllRates()

    @Update
    suspend fun update(rate: Rate)
}