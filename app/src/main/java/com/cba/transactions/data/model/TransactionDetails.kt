package com.cba.transactions.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class TransactionDetails(
    @SerializedName("amount") var amount: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("isPending") var isPending: Boolean = false,
    @SerializedName("description") var description: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("effectiveDate") var effectiveDate: String? = null

):Serializable