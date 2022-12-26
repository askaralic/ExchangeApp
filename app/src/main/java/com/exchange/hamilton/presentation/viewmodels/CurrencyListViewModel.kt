package com.exchange.hamilton.presentation.viewmodels

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.exchange.hamilton.data.model.Currency
import com.exchange.hamilton.data.repository.Repository
import com.exchange.hamilton.utils.PREF_NAME
import javax.inject.Inject


class CurrencyListViewModel @Inject constructor(private val repository: Repository, app: Application) : ViewModel() {

    private val sharedPreferences: SharedPreferences = app.applicationContext.getSharedPreferences(
        PREF_NAME, Context.MODE_PRIVATE)
    var gson = Gson()
    var currencyList: Currency?
        get() {
            if(sharedPreferences.getString("currencyList", "").isNullOrEmpty()){
                return null
            }else {
                return gson.fromJson(sharedPreferences.getString("currencyList", ""),Currency::class.java)
            }
        }
        set(value) = sharedPreferences.edit().putString("currencyList", gson.toJson(value)).apply()

    suspend fun getCurrency(): Currency? {
        if(currencyList == null) {
            currencyList = repository.getCurrency()
        }
        return currencyList;
    }

    fun onConvert(view: View,from: Double){

    }
}