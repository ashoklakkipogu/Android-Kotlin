package com.cba.transactions.di.module

import android.app.Application
import android.content.Context
import com.cba.transactions.CbaApp
import com.cba.transactions.data.remote.CbaRemoteDataSource
import com.cba.transactions.data.remote.CbaService
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: CbaApp): Context = app

    @Provides
    @Singleton
    fun provideApplication(app: CbaApp): Application = app

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CbaService =
        retrofit.create(CbaService::class.java)


    @Provides
    @Singleton
    @Named("IO")
    fun provideBackgroundDispatchers(): CoroutineDispatcher =
        Dispatchers.IO

    @Provides
    @Singleton
    @Named("MAIN")
    fun provideMainDispatchers(): CoroutineDispatcher =
        Dispatchers.Main


    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: CbaService) = CbaRemoteDataSource(apiService)


}