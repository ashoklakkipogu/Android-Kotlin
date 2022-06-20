package com.cba.transactions

import android.app.Application
import com.cba.transactions.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CbaApp: DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
    }


}