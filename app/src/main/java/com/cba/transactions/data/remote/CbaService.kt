package com.cba.transactions.data.remote

import com.cba.transactions.data.model.Transaction
import retrofit2.Response
import retrofit2.http.GET
import java.util.*


interface CbaService {

    companion object {
        const val BASE_URL = "https://www.dropbox.com/s/inyr8o29shntk9w/"
    }

    @GET("exercise.json?dl=1")
    suspend fun getAllTransaction(): Response<Transaction>
}