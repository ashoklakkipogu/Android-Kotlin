package com.cba.transactions.ui.activity

import com.cba.transactions.R
import com.cba.transactions.base.BaseActivity
import com.cba.transactions.databinding.ActivityMainBinding
import com.cba.transactions.ui.fragment.ShoppingFragmentFactory
import com.cba.transactions.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    @Inject
    lateinit var fragmentFactory: ShoppingFragmentFactory
    override fun getViewModelClass(): Class<MainViewModel> {

        return MainViewModel::class.java
    }

    override fun getLayoutResourceId(): Int {

        return R.layout.activity_main
    }

    override fun initView() {
        supportFragmentManager.fragmentFactory = fragmentFactory
        //viewModel.setTitle(this, resources.getString(R.string.complete_access))

    }


}