package com.example.rateconverter.models

data class ApiRateList(val disclaimer: String,
                  val license: String,
                  val timestamp: Long,
                  val base: String,
                  val rates: Map<String, Double>) {

}