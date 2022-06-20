package com.cba.transactions.data.model

import com.google.gson.annotations.SerializedName


data class Atms(

    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("location") var location: Location? = Location(),
    @SerializedName("address") var address: String? = null

)