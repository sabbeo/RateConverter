package com.example.rateconverter.json

import android.util.Log
import com.example.rateconverter.models.ApiCurrencyList
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule

object CurrencyConverter {

    fun convertCurrency(data: String){

        // ObjectMapper
        val objectMapper = jsonMapper {
            addModule(kotlinModule())
        }
        // Deserialize JSON Data
        val result = objectMapper.readValue(
            data,
            object : TypeReference<Map<String, String>>() {})
            .map{ApiCurrencyList(it.key,  it.value)}

        Log.d("RateConverter", "Result: $result")


    }
}