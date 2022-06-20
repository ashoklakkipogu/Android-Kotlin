package com.cba.transactions.data.model

import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("account") var account: Account? = Account(),
    @SerializedName("transactions") var transactions: ArrayList<TransactionDetails> = arrayListOf(),
)