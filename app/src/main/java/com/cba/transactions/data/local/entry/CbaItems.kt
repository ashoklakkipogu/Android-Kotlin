package com.cba.transactions.data.local.entry

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "bank_data")
data class CbaItems (
    @Expose
    var amount: Float = 0f,

    @Expose
    var description: String = "",

    @Expose
    var category: String = "",

    @Expose
    var effectiveDate: String = "",

    @PrimaryKey(autoGenerate = true)
    @Expose
    var id: Int = 0

)