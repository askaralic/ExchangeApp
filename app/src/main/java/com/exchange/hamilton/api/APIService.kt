package com.exchange.hamilton.api

import com.exchange.hamilton.data.model.Currency
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    //Dont worry about response we just tell the unsplash that user downlaoded the image
    @GET("photos/{id}/download")
    suspend fun downloadImage(@Path("id") photoId: String): ResponseBody


    @GET("https://v6.exchangerate-api.com/v6/{ApiKey}/latest/USD")
    suspend fun getExchangeRatesList(@Path("ApiKey") ApiKey: String): List<Currency>

    @GET("https://v6.exchangerate-api.com/v6/{ApiKey}/latest/USD")
    suspend fun getExchangeRates(@Path("ApiKey") ApiKey: String): Currency
}