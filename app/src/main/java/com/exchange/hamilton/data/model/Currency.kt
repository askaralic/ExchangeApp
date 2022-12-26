package com.exchange.hamilton.data.model

import android.os.Parcelable
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import org.json.JSONObject
import java.util.Objects

data class Currency(
    @Expose
   public var id: String,
    @Expose
    var result: String,
    @Expose
    var time_last_update_unix: Long,
    @Expose
    var time_next_update_unix: Long,
    @Expose
    var fetchDateTime: Long,
    @Expose
    var documentation: String,
    @Expose
    var conversion_rates:JsonObject,
    @Expose
    var availableCurrencies: ArrayList<AvailableCurrencies>
)
