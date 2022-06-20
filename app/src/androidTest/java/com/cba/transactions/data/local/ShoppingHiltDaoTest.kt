package com.cba.transactions.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.cba.transactions.getOrAwaitValue
import com.cba.transactions.data.local.dao.ShoppingDao
import com.cba.transactions.data.local.entry.ShoppingItem
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import javax.inject.Inject
import javax.inject.Named


@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ShoppingHiltDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: ShoppingDatabase
    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun setup() {
        hiltRule.inject()
        shoppingDao = database.shoppingDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    //test fragment
   /* @Test
    fun testLaunchFragmentInHiltContainer(){
        launchFragmentInHiltContainer<ShoppingFragment> {

        }
    }*/

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