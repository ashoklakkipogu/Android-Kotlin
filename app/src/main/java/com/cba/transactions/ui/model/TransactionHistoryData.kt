package com.cba.transactions.ui.model

import android.view.SurfaceControl
import com.cba.transactions.data.model.Transaction
import com.cba.transactions.data.model.TransactionDetails
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TransactionHistoryData(
    var isDate: Boolean = false,
    var date: String = "",
    var days: String = "",
    var transaction: TransactionDetails? = null,
):Serializable
