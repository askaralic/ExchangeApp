package com.exchange.hamilton.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AvailableCurrencies(
    var id: String? = "",
    var CurrencyName:String?= "",
    var CurrencyAmount:Double? = 0.0,
    var shouldEnable:Boolean? = true
) : Parcelable
