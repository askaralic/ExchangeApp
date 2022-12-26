package com.exchange.hamilton.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.exchange.hamilton.data.model.Currency
import com.exchange.hamilton.data.repository.Repository
import com.exchange.hamilton.utils.PREF_NAME
import com.exchange.hamilton.utils.toDateTime
import com.exchange.hamilton.utils.toDateTimeString
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repository: Repository, app: Application) :
    AndroidViewModel(app) {
    fun convertAmount(text: String): Double {
        var result :Double = 0.0
        try {
             result = text.toDouble() * destinationCurrencyAmount
        }catch (ex:java.lang.Exception){

        }
       return result;
    }
    fun convertAmount(text: Float): Double {
        var result :Double = 0.0
        try {
            result = text.toDouble() * destinationCurrencyAmount
        }catch (ex:java.lang.Exception){

        }
        return result;
    }
    var operation =0;


    private val sharedPreferences: SharedPreferences = app.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var gson = Gson()
    var currencyList: Currency?
        get() {
            if(sharedPreferences.getString("currencyList", "").isNullOrEmpty()){
                return null
            }else {
                return gson.fromJson(sharedPreferences.getString("currencyList", ""), Currency::class.java)
            }
        }
        set(value) = sharedPreferences.edit().putString("currencyList", gson.toJson(value)).apply()

    suspend fun getCurrency(): Currency? {
        if(currencyList == null || currencyList!!.fetchDateTime.toDateTime().plusHours(5).isBeforeNow) {
            currencyList = repository.getCurrency()
        }
        Log.d("last fetched", currencyList!!.fetchDateTime.toDateTime().plusSeconds(30).toDateTimeString())
        return currencyList;
    }

    var sourceCurrencyAmount: Float
        get() = sharedPreferences.getFloat("sourceCurrencyAmount", 0.0F)
        set(value) = sharedPreferences.edit().putFloat("sourceCurrencyAmount", value).apply()


    var sourceCurrency: String?
        get() = sharedPreferences.getString("sourceCurrency", "")
        set(value) = sharedPreferences.edit().putString("sourceCurrency", value).apply()


    var destinationCurrencyAmount: Float
        get() = sharedPreferences.getFloat("destinationCurrencyAmount", 0.0F)
        set(value) = sharedPreferences.edit().putFloat("destinationCurrencyAmount", value).apply()

     var approvalDuration: Long
        get() = sharedPreferences.getLong("approvalDuration", 30)
        set(value) = sharedPreferences.edit().putLong("approvalDuration", value).apply()

    var destinationCurrency: String?
        get() = sharedPreferences.getString("destinationCurrency", "")
        set(value) = sharedPreferences.edit().putString("destinationCurrency", value).apply()
}