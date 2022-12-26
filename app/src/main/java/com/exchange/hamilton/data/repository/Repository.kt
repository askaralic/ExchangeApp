package com.exchange.hamilton.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.exchange.hamilton.api.APIService
import com.exchange.hamilton.data.datasource.CurrencyDataSource
import com.exchange.hamilton.data.model.AvailableCurrencies
import com.exchange.hamilton.data.model.Currency
import com.exchange.hamilton.utils.EXCHANGE_API_KEY
import com.exchange.hamilton.utils.LOAD_SIZE
import org.joda.time.DateTime
import javax.inject.Inject


class Repository @Inject constructor(private val APIService: APIService) {


    fun getCurrencyList(orderBy: String) = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = LOAD_SIZE),
        pagingSourceFactory = {
            CurrencyDataSource(APIService, orderBy)
        }
    ).flow

    suspend fun getCurrency():Currency {
       val currency = APIService.getExchangeRates(EXCHANGE_API_KEY)
        currency.fetchDateTime = DateTime.now().millis
        var item: AvailableCurrencies

        currency.availableCurrencies = ArrayList()

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

        return currency;
    }

    suspend fun sendDownload(id: String) {
        APIService.downloadImage(id)
    }


}