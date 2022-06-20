package com.cba.transactions.viewmodel

import android.graphics.Typeface
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cba.transactions.R
import com.cba.transactions.common.Result
import com.cba.transactions.data.ShoppingRepository
import com.cba.transactions.data.remote.response.ImageResponse
import com.cba.transactions.ui.activity.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel @ViewModelInject constructor(
    private val repository: ShoppingRepository?
) : ViewModel() {
    public val transaction = MutableLiveData<Result<ImageResponse>>()

    private fun setResultTransaction(result: Result<ImageResponse>) {
        transaction.postValue(result)
    }

    internal fun observeGetAllTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                setResultTransaction(Result.loading<ImageResponse>())
                withContext(Dispatchers.Main) {
                    val result = repository!!.searchForImage("yellow flowers")
                    setResultTransaction(Result.success(result.data!!))
                }
            } catch (e: Throwable) {
                setResultTransaction(Result.error(e.message ?: "Unknown error"))
            }
        }
    }


    public fun setTitle(activity: MainActivity, title: String?) {
        activity.supportActionBar!!.setHomeButtonEnabled(true)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val textView = TextView(activity)
        textView.text = title
        textView.textSize = 20f
        textView.setTypeface(null, Typeface.BOLD)
        textView.layoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        textView.gravity = Gravity.CENTER
        textView.setTextColor(ContextCompat.getColor(activity, R.color.black))
        activity.supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        activity.supportActionBar!!.customView = textView
    }



}