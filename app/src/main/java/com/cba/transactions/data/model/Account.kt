package com.cba.transactions.data.model

import com.google.gson.annotations.SerializedName


data class Account(

    @SerializedName("bsb") var bsb: String? = null,
    @SerializedName("accountNumber") var accountNumber: String? = null,
    @SerializedName("balance") var balance: String? = null,
    @SerializedName("available") var available: String? = null,
    @SerializedName("accountName") var accountName: String? = null,
    var pendingBal: Float? = null

)