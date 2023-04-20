package com.example.rateconverter.json

import android.util.Log
import com.example.rateconverter.models.ApiRateList
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule

object RateConverter {

     fun convertRates(data: String){

        // ObjectMapper
        val objectMapper = jsonMapper {
            addModule(kotlinModule())
        }
        // Deserialize JSON Data
        val result: ApiRateList = objectMapper.readValue(
            data,
            ApiRateList::class.java
        )
        Log.d("RateConverter", "Result: $result")

    }
}