package com.exchange.hamilton.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import com.exchange.hamilton.BuildConfig
import com.exchange.hamilton.api.APIService
import com.exchange.hamilton.utils.Config
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
object NetworkModule {

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun providesOkhttp(cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(cacheInterceptor)
            .cache(cache)
            .addInterceptor { chain: Interceptor.Chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Client-ID " + Config.unsplash_access_key)
                    .build()
                chain.proceed(newRequest)
            }
        if (BuildConfig.DEBUG) builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(client: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(Config.BASE_URL_UNSPLASH)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): APIService =
        retrofit.create(APIService::class.java)

    @Provides
    @Singleton
    fun provideCache(app: Application): Cache {
        return Cache(
            File(app.applicationContext.cacheDir, "unSplash_images_cache"),
            10 * 1024 * 1024
        )
    }

    private val cacheInterceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val response: Response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(30, TimeUnit.DAYS)
                .build()
            return response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }


}