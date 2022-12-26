package com.exchange.hamilton.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.exchange.hamilton.api.APIService
import com.exchange.hamilton.data.model.Currency
import com.exchange.hamilton.utils.EXCHANGE_API_KEY
import com.exchange.hamilton.utils.STARTING_PAGE
import java.io.IOException


class CurrencyDataSource(private val APIService: APIService, private val orderBy: String) : PagingSource<Int, Currency>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Currency> {
        val page = params.key ?: STARTING_PAGE

        return try {
            val data = APIService.getExchangeRatesList(EXCHANGE_API_KEY)

            LoadResult.Page(
                data = data,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )

        } catch (throwable: Throwable) {
            var exception = throwable
            if (throwable is IOException) {
                exception = IOException("Please check internet connection")
            }
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Currency>): Int? {
        TODO("Not yet implemented")
    }

}