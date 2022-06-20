package com.cba.transactions.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cba.transactions.data.local.dao.ShoppingDao
import com.cba.transactions.data.local.entry.ShoppingItem


@Database(entities = [ShoppingItem::class], exportSchema = false, version = ShoppingDatabase.VERSION)
abstract class ShoppingDatabase: RoomDatabase() {

    companion object {
        const val VERSION = 1
        const val DATABASE = "shopping.db"
    }

    abstract fun shoppingDao(): ShoppingDao


}