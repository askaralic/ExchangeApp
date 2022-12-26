package com.exchange.hamilton.data.model

import com.google.gson.annotations.Expose

data class AvailableCurrencies(
    @Expose
    var id: String? = "",
    @Expose
    var CurrencyName:String?= "",
    @Expose
    var CurrencyAmount: Float = 0.0f,
    @Expose
    var shouldEnable:Boolean? = true
)
