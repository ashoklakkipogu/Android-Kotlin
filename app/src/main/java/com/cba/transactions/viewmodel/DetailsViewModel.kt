package com.cba.transactions.viewmodel

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.speech.RecognizerIntent
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cba.transactions.R
import com.cba.transactions.common.FontType
import com.cba.transactions.data.CbaRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named
import com.cba.transactions.common.Result
import com.cba.transactions.data.model.Transaction
import com.cba.transactions.ui.activity.MainActivity
import com.cba.transactions.ui.activity.TransactionDetailsActivity
import com.cba.transactions.ui.model.BankData
import com.cba.transactions.ui.model.TransactionHistoryData
import com.cba.transactions.utils.Utils
import java.util.*

class DetailsViewModel @Inject constructor(
    private val repository: CbaRepository,
    @Named("MAIN") private val main: CoroutineDispatcher
) : ViewModel() {
    public val transaction = MutableLiveData<Result<BankData>>()

    private fun setResultTransaction(result: Result<BankData>) {
        transaction.postValue(result)
    }

    internal fun observeGetAllTransactions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                setResultTransaction(Result.loading<BankData>())
                withContext(Dispatchers.Main) {
                    val result = repository.getTransactions()
                    val bankData = getBankDetails(result.data!!)
                    setResultTransaction(Result.success(bankData))
                }
            } catch (e: Throwable) {
                setResultTransaction(Result.error(e.message ?: "Unknown error"))
            }
        }
    }

    private fun getBankDetails(data: Transaction): BankData {
        val bankData = BankData()
        bankData.account = data.account
        val transactions = data.transactions
        var result = transactions.groupBy({ it.effectiveDate }, { it })
        result = result.toSortedMap(compareByDescending { it })
        val transactionFilterList: ArrayList<TransactionHistoryData> = ArrayList()
        var pendingBal = 0F
        for ((k, v) in result) {
            var mTransactionFilterData = TransactionHistoryData()
            mTransactionFilterData.isDate = true
            mTransactionFilterData.date = Utils.convertDate(k!!)
            mTransactionFilterData.days = Utils.covertTimeToText(k)
            transactionFilterList.add(mTransactionFilterData)
            val value = v.sortedBy { it.isPending }.reversed()
            for (obj in value) {
                mTransactionFilterData = TransactionHistoryData()
                mTransactionFilterData.isDate = false
                mTransactionFilterData.transaction = obj
                if (obj.isPending) {
                    pendingBal += obj.amount?.toFloat() ?: 0f
                }
                transactionFilterList.add(mTransactionFilterData)
            }
        }
        data.account?.pendingBal = pendingBal
        bankData.transaction = transactionFilterList
        return bankData
    }

    public fun setTitle(activity: TransactionDetailsActivity, title: String?) {
        activity.supportActionBar!!.setHomeButtonEnabled(true)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar!!.setDisplayShowHomeEnabled(true);

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

    public fun promptSpeechInput(activity: MainActivity) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            activity.getString(R.string.speech_prompt)
        )
        try {
            activity.resultLauncher.launch(intent);
        } catch (a: ActivityNotFoundException) {
            Toast.makeText(
                activity,
                activity.getString(R.string.speech_not_supported),
                Toast.LENGTH_SHORT
            ).show()
        }
    }


}