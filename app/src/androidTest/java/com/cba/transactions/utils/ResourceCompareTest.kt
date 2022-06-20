package com.cba.transactions.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.cba.transactions.R
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceCompareTest{

    private lateinit var resourceCompare: ResourceCompare
    @Before
    fun setUp(){
        resourceCompare = ResourceCompare()
    }

    @After
    fun teardown(){

    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context, R.string.app_name, "CBA")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceSameAsGivenString_returnsFalse(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context, R.string.app_name, "TEST")
        assertThat(result).isFalse()
    }
}