package com.cba.transactions.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cba.transactions.di.ViewModelKey
import com.cba.transactions.di.factory.ViewModelFactory
import com.cba.transactions.viewmodel.DetailsViewModel
import com.cba.transactions.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun providesMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    internal abstract fun providesDetailsViewModel(viewModel: DetailsViewModel): ViewModel


}