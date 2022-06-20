package com.cba.transactions.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.cba.transactions.getOrAwaitValue
import com.cba.transactions.data.local.dao.ShoppingDao
import com.cba.transactions.data.local.entry.ShoppingItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CbaDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingDatabase
    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppingDao = database.shoppingDao()
    }

    @After
    fun teardown() {
       // database.close()
    }

    @Test
    fun insertCbaItem() = runBlockingTest {
        val item = ShoppingItem("test", 100, 2f, "", 1)
        shoppingDao.insertShoppingItem(item)

        val allItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allItems).contains(item)
    }

    @Test
    fun deleteCbaItem() = runBlockingTest {
        val item = ShoppingItem("test", 100, 2f, "")
        shoppingDao.insertShoppingItem(item)
        shoppingDao.deleteShoppingItem(item)

        val allCbaItems = shoppingDao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allCbaItems).doesNotContain(shoppingDao)
    }


    @Test
    fun observeTotalPriceSum() = runBlockingTest {
        val item1 = ShoppingItem("test", 50, 2f, "",1)
        val item2 = ShoppingItem("test", 40, 2f, "", 2)
        val item3 = ShoppingItem("test", 100, 3f, "", 3)

        shoppingDao.insertShoppingItem(item1)
        shoppingDao.insertShoppingItem(item2)
        shoppingDao.insertShoppingItem(item3)

        val totalPriceSum = shoppingDao.observeTotalPrice().getOrAwaitValue()

        assertThat(totalPriceSum).isEqualTo(50*2f+40*2f+100*3f)
    }

}