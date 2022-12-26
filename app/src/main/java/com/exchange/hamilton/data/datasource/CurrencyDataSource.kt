package com.exchange.hamilton.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.exchange.hamilton.api.APIService
import com.exchange.hamilton.data.model.Currency
import com.exchange.hamilton.utils.EXCHANGE_API_KEY
import retrofit2.Response
import java.io.IOException


class CurrencyDataSource(private val APIService: APIService)  {

    suspend fun getExchangeRates(): Response<Currency> {
        return APIService.getExchangeRates(EXCHANGE_API_KEY)
    }
}