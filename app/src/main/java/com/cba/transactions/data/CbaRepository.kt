package com.cba.transactions.data

import com.cba.transactions.data.remote.CbaRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CbaRepository @Inject constructor(
    private val remote: CbaRemoteDataSource
) {
    suspend fun getTransactions() = remote.getAllTransaction()
}