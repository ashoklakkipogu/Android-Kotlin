package com.cba.transactions.data.local.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.cba.transactions.data.local.entry.CbaItems

@Dao
interface CbaDao {
    @Query("SELECT * FROM bank_data")
    fun getAllBankDetails(): LiveData<List<CbaItems>>

    @Query("SELECT * FROM bank_data WHERE id =:id")
    fun getBankDetailsById(id: Int): LiveData<List<CbaItems>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBankDetails(bank: CbaItems)

    @Delete
    fun deleteBibleContent(bank: CbaItems)

    @Query("SELECT SUM(amount) FROM bank_data")
    fun observeTotalPrice(): LiveData<Float>

}