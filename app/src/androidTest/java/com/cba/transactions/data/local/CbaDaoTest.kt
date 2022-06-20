package com.cba.transactions.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.cba.transactions.data.getOrAwaitValue
import com.cba.transactions.data.local.dao.CbaDao
import com.cba.transactions.data.local.entry.CbaItems
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

    private lateinit var database: CbaDatabase
    private lateinit var cbaDao: CbaDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CbaDatabase::class.java
        ).allowMainThreadQueries().build()
        cbaDao = database.cbaDao()
    }

    @After
    fun teardown() {
       // database.close()
    }

    @Test
    fun insertCbaItem() = runBlockingTest {
        val cba = CbaItems(100f, "test", "category", "12/01/2022", 1)
        cbaDao.insertBankDetails(cba)

        val allCbaItems = cbaDao.getAllBankDetails().getOrAwaitValue()
        assertThat(allCbaItems).contains(cba)
    }

    @Test
    fun deleteCbaItem() = runBlockingTest {
        val cba = CbaItems(100f, "test", "category", "12/01/2022", 1)
        cbaDao.insertBankDetails(cba)
        cbaDao.deleteBibleContent(cba)

        val allCbaItems = cbaDao.getAllBankDetails().getOrAwaitValue()
        assertThat(allCbaItems).doesNotContain(cba)
    }


    @Test
    fun observeTotalPriceSum() = runBlockingTest {
        val bank1 = CbaItems(100f, "test", "category", "12/01/2022", 1)
        val bank2 = CbaItems(100f, "test", "category", "12/01/2022", 2)
        val bank3 = CbaItems(100f, "test", "category", "12/01/2022", 3)
        cbaDao.insertBankDetails(bank1)
        cbaDao.insertBankDetails(bank2)
        cbaDao.insertBankDetails(bank3)

        val totalPriceSum = cbaDao.observeTotalPrice().getOrAwaitValue()

        assertThat(totalPriceSum).isEqualTo(300f)
    }

}