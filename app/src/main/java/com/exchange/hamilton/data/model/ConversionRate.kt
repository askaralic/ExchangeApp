package com.exchange.hamilton.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

data class ConversionRate(
    @Expose
    var USD: Double,
    @Expose
    var AED: Double,
    @Expose
    var AFN: Double,
    @Expose
    var ALL: Double,
    @Expose
    var AMD: Double,
    @Expose
    var ANG: Double,
    @Expose
    var AOA: Double,
    @Expose
    var ARS: Double,
    @Expose
    var AUD: Double,
)
