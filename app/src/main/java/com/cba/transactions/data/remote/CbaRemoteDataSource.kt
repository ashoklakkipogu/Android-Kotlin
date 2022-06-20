package com.cba.transactions.data.remote

import com.cba.transactions.base.BaseDataSource

class CbaRemoteDataSource constructor(private val service: CbaService) :
    BaseDataSource() {

    suspend fun getAllTransaction() = getResult {
        service.getAllTransaction()
    }
}