package com.example.rateconverter.json

import com.example.rateconverter.models.ApiCurrencyList
import com.example.rateconverter.models.Rate
import com.example.rateconverter.models.ApiRateList
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule

object MergeConverter {
    private var latestId = 0
    fun mergeApiData(currencyJsonData: String, rateJsonData: String): MutableList<Rate> {

        val objectMapper = jsonMapper {
            addModule(kotlinModule())
        }

        val currencyList = objectMapper.readValue(
            currencyJsonData,
            object : TypeReference<Map<String, String>>() {})

        val rateList = objectMapper.readValue(
            rateJsonData,
            ApiRateList::class.java
        )

        val rateObjects = mutableListOf<Rate>()

        rateList.rates?.forEach { (code, price) ->
            //val currency = currencyList.filter { it.key == code  }
            val currency = currencyList[code]
            if (currency != null) {
                val name = "${currency} ($code)"
                val rate = Rate(latestId++, name, price, code)
                rateObjects.add(rate)
            }
        }

        return rateObjects
    }
}