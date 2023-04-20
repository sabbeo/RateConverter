package com.example.rateconverter.models

import android.widget.ImageView

class Rate(var id: Int, var name: String, var price: Double, var code: String){
    var flag: String = code.lowercase() + ".png"
}