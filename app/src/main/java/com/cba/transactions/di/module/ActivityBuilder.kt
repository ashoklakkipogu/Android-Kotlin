package com.cba.transactions.di.module

import com.cba.transactions.ui.activity.MainActivity
import com.cba.transactions.ui.activity.TransactionDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesTransactionDetailsActivity(): TransactionDetailsActivity

}