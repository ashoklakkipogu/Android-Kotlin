package com.cba.transactions.ui.activity

import androidx.core.content.ContextCompat
import com.cba.transactions.R
import com.cba.transactions.base.BaseActivity
import com.cba.transactions.data.model.TransactionDetails
import com.cba.transactions.databinding.ActivityDetailsBinding
import com.cba.transactions.di.injectViewModelDepen
import com.cba.transactions.utils.Utils
import com.cba.transactions.viewmodel.DetailsViewModel


class TransactionDetailsActivity : BaseActivity<ActivityDetailsBinding, DetailsViewModel>() {
    override fun injectViewModel() {
        mViewModel = injectViewModelDepen(viewModelFactory)
    }

    override fun getViewModelClass(): Class<DetailsViewModel> {
        return DetailsViewModel::class.java
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_details
    }

    override fun initView() {
        viewModel.setTitle(this, resources.getString(R.string.transaction_details))
        val bundle = intent.extras
        val transaction = bundle?.getSerializable("TRANSACTION") as TransactionDetails
        binding.obj = transaction

        transaction.effectiveDate?.let {
            binding.date.text = Utils.convertDate(it)
        }
        if (transaction.isPending){
            binding.pendingLabel.text = "Pending"
            binding.pendingLabel.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        }else{
            binding.pendingLabel.text = "Completed"
            binding.pendingLabel.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        }
        observeUi()

    }

    private fun observeUi() {


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}