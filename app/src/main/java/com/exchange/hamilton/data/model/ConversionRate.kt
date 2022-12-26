package com.exchange.hamilton.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConversionRate(
    var USD: Double,
    var AED: Double,
    var AFN: Double,
    var ALL: Double,
    var AMD: Double,
    var ANG: Double,
    var AOA: Double,
    var ARS: Double,
    var AUD: Double,
) : Parcelable
