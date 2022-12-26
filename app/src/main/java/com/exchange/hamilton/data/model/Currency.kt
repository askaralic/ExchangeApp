package com.exchange.hamilton.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    var id: String,
    var result: String,
    var time_last_update_unix: Long,
    var time_next_update_unix: Long,
    var fetchDateTime: Long,
    var documentation: String,
    var conversion_rates:ConversionRate,
    var availableCurrencies: ArrayList<AvailableCurrencies>
) : Parcelable
