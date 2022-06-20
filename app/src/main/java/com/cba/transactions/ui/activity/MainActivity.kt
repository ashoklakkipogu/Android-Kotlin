package com.cba.transactions.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cba.transactions.R
import com.cba.transactions.base.BaseActivity
import com.cba.transactions.common.FontType
import com.cba.transactions.common.Result
import com.cba.transactions.data.local.dao.CbaDao
import com.cba.transactions.data.local.entry.CbaItems
import com.cba.transactions.data.model.TransactionDetails
import com.cba.transactions.databinding.ActivityMainBinding
import com.cba.transactions.di.injectViewModelDepen
import com.cba.transactions.ui.adapter.TransactionAdapter
import com.cba.transactions.ui.model.TransactionHistoryData
import com.cba.transactions.viewmodel.MainViewModel
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private var transactionFilterList: ArrayList<TransactionHistoryData> = ArrayList()
    private lateinit var adapter: TransactionAdapter

    @Inject
    lateinit var cbaDao: CbaDao
    override fun injectViewModel() {
        mViewModel = injectViewModelDepen(viewModelFactory)
    }

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        viewModel.setTitle(this, resources.getString(R.string.complete_access))
        adapter = TransactionAdapter(this, transactionFilterList)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,
                false
            )

        binding.adapter = adapter
        observeUi()
        binding.pullToRefresh.setOnRefreshListener {
            viewModel.observeGetAllTransactions()
        }

        binding.fab.setOnClickListener {
            viewModel.promptSpeechInput(this)
        }

       /* val cba = CbaItems(100f, "test", "category", "12/01/2022", 1)
        cbaDao.insertBankDetails(cba)*/
    }

    private fun observeUi() {
        with(viewModel) {
            observeGetAllTransactions()
            transaction.observe(this@MainActivity, Observer { result ->
                when (result.status) {
                    Result.Status.SUCCESS -> {
                        loadingView(false)
                        val transactionData = result.data
                        if (transactionData != null) {
                            binding.account = transactionData.account
                            adapter.updateData(transactionData.transaction)
                        }
                        binding.pullToRefresh.isRefreshing = false
                    }

                    Result.Status.ERROR -> {
                        loadingView(false)
                        binding.pullToRefresh.isRefreshing = false
                    }

                    Result.Status.LOADING -> {
                        if (!binding.pullToRefresh.isRefreshing)
                            loadingView(true)
                    }
                }
            })
        }

    }

    private fun loadingView(isLoader: Boolean) {
        if (isLoader) {
            binding.shimmerBg.visibility = View.VISIBLE
            binding.shimmer.visibility = View.VISIBLE
            binding.shimmer.startShimmer()
        } else {
            binding.shimmerBg.visibility = View.GONE
            binding.shimmer.visibility = View.GONE
            binding.shimmer.stopShimmer()
        }
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                val inputType = result?.get(0)?.toLowerCase()

                when {
                    inputType.equals("small") -> adjustFontScale(FontType.SMALL)
                    inputType.equals("default") -> adjustFontScale(FontType.DEFAULT)
                    inputType.equals("large") -> adjustFontScale(FontType.LARGE)
                    inputType.equals("extra large") -> adjustFontScale(FontType.EXTRA_LARGE)
                    else -> Toast.makeText(
                        applicationContext,
                        "Please speak (SMALL, DEFAULT, LARGE OR EXTRA LARGE)",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    private fun adjustFontScale(type: FontType) {
        when (type) {
            FontType.SMALL -> {
                setFont(0F)
            }
            FontType.DEFAULT -> {
                setFont(2F)
            }
            FontType.LARGE -> {
                setFont(4F)
            }
            FontType.EXTRA_LARGE -> {
                setFont(8F)
            }
        }

    }

    private fun setFont(fontS: Float) {
        binding.availableLabel.textSize = 12.0F + fontS
        binding.availableBal.textSize = 14.0F + fontS
        binding.balLabel.textSize = 12.0F + fontS
        binding.balVal.textSize = 12.0F + fontS
        binding.bsbLabel.textSize = 12.0F + fontS
        binding.bsbVal.textSize = 12.0F + fontS
        binding.accLabel.textSize = 12.0F + fontS
        binding.accVal.textSize = 12.0F + fontS
        adapter.updateFontSize(fontS)
    }

    fun onClickTransaction(obj: TransactionDetails?) {
        val intent = Intent(this, TransactionDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("TRANSACTION", obj)
        intent.putExtras(bundle)
        startActivity(intent)
    }


}