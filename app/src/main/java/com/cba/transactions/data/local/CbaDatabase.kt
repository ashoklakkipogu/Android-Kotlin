package com.cba.transactions.data.local

import android.content.SharedPreferences
import androidx.room.Database
import androidx.room.RoomDatabase
import com.cba.transactions.data.local.dao.CbaDao
import com.cba.transactions.data.local.entry.CbaItems
import javax.inject.Inject


@Database(entities = [CbaItems::class], exportSchema = false, version = CbaDatabase.VERSION)
abstract class CbaDatabase: RoomDatabase() {

    companion object {
        const val VERSION = 1
        const val DATABASE = "cba.db"
    }

    abstract fun cbaDao(): CbaDao


}