package com.cba.transactions.data

import androidx.lifecycle.LiveData
import com.cba.transactions.base.BaseDataSource
import com.cba.transactions.common.Result
import com.cba.transactions.data.local.dao.ShoppingDao
import com.cba.transactions.data.local.entry.ShoppingItem
import com.cba.transactions.data.remote.ShoppingService
import com.cba.transactions.data.remote.response.ImageResponse
import javax.inject.Inject

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Result<ImageResponse>
}

class ShoppingRepositoryImp @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val shoppingAPI: ShoppingService
) : ShoppingRepository, BaseDataSource() {
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery:String): Result<ImageResponse> {
        return getResult { shoppingAPI.searchForImage(imageQuery) }
    }
}