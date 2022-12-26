package com.exchange.hamilton.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.provider.SyncStateContract.Constants
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.exchange.hamilton.data.model.AvailableCurrencies
import com.google.gson.Gson
import com.exchange.hamilton.data.model.Currency
import com.exchange.hamilton.data.repository.Repository
import com.exchange.hamilton.utils.*
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repository: Repository, app: Application) :
    AndroidViewModel(app) {
    fun convertAmount(text: String): Double {
        var result :Double = 0.0
        try {
            result = text.toDouble() * (selectedDestinationCurrency?.CurrencyAmount ?: 0f)
        }catch (ex:java.lang.Exception){

        }
        return result;
    }
    fun convertAmount(amountToConvert: Float): Float {
        var result :Float = 0.0f
        try {
            result = getConversionRate() * amountToConvert
        }catch (ex:java.lang.Exception){

        }
        return result;
    }

     fun getConversionRate(): Float {
       return (selectedDestinationCurrency?.CurrencyAmount ?: 0f)/(selectedSourceCurrency?.CurrencyAmount ?: 0f)
    }

    var operation =0;


    private val sharedPreferences: SharedPreferences = app.applicationContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var gson = Gson()
    var currencyList: Currency?
        get() {
            if(sharedPreferences.getString(SHARED_PREFERENCE_KEY_LAST_FETCHED_CURRENCY_RESPONSE, "").isNullOrEmpty()){
                return null
            }else {
                return gson.fromJson(sharedPreferences.getString(SHARED_PREFERENCE_KEY_LAST_FETCHED_CURRENCY_RESPONSE, ""), Currency::class.java)
            }
        }
        set(value) = sharedPreferences.edit().putString(SHARED_PREFERENCE_KEY_LAST_FETCHED_CURRENCY_RESPONSE, gson.toJson(value)).apply()

    suspend fun getCurrency(): Currency? {
        if(currencyList == null || currencyList!!.fetchDateTime.toDateTime().plusHours(5).isBeforeNow) {
            currencyList = repository.getCurrency()
        }
       return currencyList;
    }

    var approvalDuration: Long
        get() = sharedPreferences.getLong(SHARED_PREFERENCE_KEY_APPROVAL_DURATION, 30)
        set(value) = sharedPreferences.edit().putLong(SHARED_PREFERENCE_KEY_APPROVAL_DURATION, value).apply()



     var amountToConvert: Float
        get() = sharedPreferences.getFloat(SHARED_PREFERENCE_KEY_AMOUNT_TO_CONVERT, 0.0f)
        set(value) = sharedPreferences.edit().putFloat(SHARED_PREFERENCE_KEY_AMOUNT_TO_CONVERT, value).apply()



    var selectedSourceCurrency: AvailableCurrencies?
        get() {
            if(sharedPreferences.getString(SHARED_PREFERENCE_KEY_SELECTED_SOURCE_CURRENCY, "").isNullOrEmpty()){
                return null
            }else {
                return gson.fromJson(sharedPreferences.getString(SHARED_PREFERENCE_KEY_SELECTED_SOURCE_CURRENCY, ""), AvailableCurrencies::class.java)
            }
        }
        set(value) = sharedPreferences.edit().putString(SHARED_PREFERENCE_KEY_SELECTED_SOURCE_CURRENCY, gson.toJson(value)).apply()


    var selectedDestinationCurrency: AvailableCurrencies?
        get() {
            if(sharedPreferences.getString(SHARED_PREFERENCE_KEY_SELECTED_DESTINATION_CURRENCY, "").isNullOrEmpty()){
                return null
            }else {
                return gson.fromJson(sharedPreferences.getString(SHARED_PREFERENCE_KEY_SELECTED_DESTINATION_CURRENCY, ""), AvailableCurrencies::class.java)
            }
        }
        set(value) = sharedPreferences.edit().putString(SHARED_PREFERENCE_KEY_SELECTED_DESTINATION_CURRENCY, gson.toJson(value)).apply()
}