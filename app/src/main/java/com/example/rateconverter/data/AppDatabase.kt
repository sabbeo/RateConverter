package com.example.rateconverter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rateconverter.models.Rate

@Database(entities = [Rate::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rateDao(): RateDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "app_database"
                    ).build()
                }
            }
            return instance!!
        }
    }
}