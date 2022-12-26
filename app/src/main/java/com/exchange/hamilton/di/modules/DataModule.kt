package com.exchange.hamilton.di.modules

import dagger.Module
import dagger.Provides
import com.exchange.hamilton.api.APIService
import com.exchange.hamilton.data.repository.Repository
import javax.inject.Singleton


@Module
object DataModule {

    @Singleton
    @Provides
    fun providesRepository(APIService: APIService): Repository =
        Repository(APIService)
}