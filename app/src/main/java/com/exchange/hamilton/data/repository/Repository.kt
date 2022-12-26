package com.exchange.hamilton.data.repository

import android.util.Log
import android.widget.Toast
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.exchange.hamilton.api.APIService
import com.exchange.hamilton.data.datasource.CurrencyDataSource
import com.exchange.hamilton.data.model.AvailableCurrencies
import com.exchange.hamilton.data.model.Currency
import com.exchange.hamilton.utils.EXCHANGE_API_KEY
import org.joda.time.DateTime
import javax.inject.Inject


class Repository @Inject constructor(private val APIService: APIService) {


 suspend fun getCurrency(): Currency? {
  var currency: Currency? = null;

  try {
   val response = APIService.getExchangeRates(EXCHANGE_API_KEY)
   if (response.isSuccessful()) {
    if(response.body() !=null) {
     currency = response.body()!!
     currency.fetchDateTime = DateTime.now().millis
     currency.conversion_rates
     currency = prepareAvailableCurrencyList(currency)
    }
   }
  }catch (Ex:Exception){
   Log.e("Error",Ex.localizedMessage)
  }
  return currency;
 }
 private fun prepareAvailableCurrencyList(currency: Currency): Currency {
  currency.availableCurrencies = ArrayList()

  currency.conversion_rates.keySet().forEach {
   try {
    val item = AvailableCurrencies()
    item.CurrencyAmount = currency.conversion_rates.get(it).asFloat
    item.CurrencyName = it
    currency.availableCurrencies.add(item)
   }catch (_:Exception){

   }
  }
  return currency;
 }
/* private fun prepareAvailableCurrencyList(currency: Currency) {
  currency.availableCurrencies = ArrayList()
  var item: AvailableCurrencies
  item = AvailableCurrencies()
  item.CurrencyAmount = currency.conversion_rates.USD
  item.CurrencyName = "USD"
  currency.availableCurrencies.add(item)

  item = AvailableCurrencies()
  item.CurrencyAmount = currency.conversion_rates.AED
  item.CurrencyName = "AED"
  currency.availableCurrencies.add(item)

  item = AvailableCurrencies()
  item.CurrencyAmount = currency.conversion_rates.AFN
  item.CurrencyName = "AFN"
  currency.availableCurrencies.add(item)

  item = AvailableCurrencies()
  item.CurrencyAmount = currency.conversion_rates.ALL
  item.CurrencyName = "ALL"
  currency.availableCurrencies.add(item)

  item = AvailableCurrencies()
  item.CurrencyAmount = currency.conversion_rates.AMD
  item.CurrencyName = "AMD"
  currency.availableCurrencies.add(item)

  item = AvailableCurrencies()
  item.CurrencyAmount = currency.conversion_rates.ANG
  item.CurrencyName = "ANG"
  currency.availableCurrencies.add(item)

  item = AvailableCurrencies()
  item.CurrencyAmount = currency.conversion_rates.AOA
  item.CurrencyName = "AOA"
  currency.availableCurrencies.add(item)

  item = AvailableCurrencies()
  item.CurrencyAmount = currency.conversion_rates.ARS
  item.CurrencyName = "ARS"
  currency.availableCurrencies.add(item)

  item = AvailableCurrencies()
  item.CurrencyAmount = currency.conversion_rates.AUD
  item.CurrencyName = "AUD"
  currency.availableCurrencies.add(item)

 }*/


}