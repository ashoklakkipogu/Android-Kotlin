package com.cba.transactions.data.remote

import com.cba.transactions.BuildConfig
import com.cba.transactions.data.remote.response.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ShoppingService {

    companion object {
        const val BASE_URL = "https://pixabay.com"
    }

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEY
    ): Response<ImageResponse>
}