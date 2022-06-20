package com.cba.transactions.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cba.transactions.common.Result
import com.cba.transactions.data.ShoppingRepository
import com.cba.transactions.data.local.entry.ShoppingItem
import com.cba.transactions.data.remote.response.ImageResponse
import com.cba.transactions.utils.Constants
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {
    public val transaction = MutableLiveData<Result<ImageResponse>>()
    val shoppingItems = repository.observeAllShoppingItems()
    val insertShoppingItemStatus = MutableLiveData<Result<ShoppingItem>>()
    val images = MutableLiveData<Result<ImageResponse>>()

    val curImageUrl = MutableLiveData<String>()




    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }


    fun insertShoppingItem(name: String, amountString: String, priceString: String) {
        if(name.isEmpty() || amountString.isEmpty() || priceString.isEmpty()) {
            insertShoppingItemStatus.postValue(Result.error("The fields must not be empty", null))
            return
        }
        if(name.length > Constants.MAX_NAME_LENGTH) {
            insertShoppingItemStatus.postValue(Result.error("The name of the item" +
                    "must not exceed ${Constants.MAX_NAME_LENGTH} characters", null))
            return
        }
        if(priceString.length > Constants.MAX_PRICE_LENGTH) {
            insertShoppingItemStatus.postValue(Result.error("The price of the item" +
                    "must not exceed ${Constants.MAX_PRICE_LENGTH} characters", null))
            return
        }
        val amount = try {
            amountString.toInt()
        } catch(e: Exception) {
            insertShoppingItemStatus.postValue(Result.error("Please enter a valid amount", null))
            return
        }
        val shoppingItem = ShoppingItem(name, amount, priceString.toFloat(), curImageUrl.value ?: "")
        insertShoppingItemIntoDb(shoppingItem)
        setCurImageUrl("")
        insertShoppingItemStatus.postValue(Result.success(shoppingItem))
    }

    fun setCurImageUrl(url: String) {
        curImageUrl.postValue(url)
    }


    fun searchForImage(imageQuery: String) {
        if(imageQuery.isEmpty()) {
            return
        }
        images.value = Result.loading(null)
        viewModelScope.launch {
            val response = repository.searchForImage(imageQuery)
            images.value = response
        }
    }

}