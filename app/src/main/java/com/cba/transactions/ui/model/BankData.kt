package com.cba.transactions.ui.model

import android.view.SurfaceControl
import com.cba.transactions.data.model.Account
import com.cba.transactions.data.model.Transaction
import com.cba.transactions.data.model.TransactionDetails
import com.google.gson.annotations.SerializedName

data class BankData(
    var account: Account? = null,
    var transaction: List<TransactionHistoryData> = ArrayList(),
)
