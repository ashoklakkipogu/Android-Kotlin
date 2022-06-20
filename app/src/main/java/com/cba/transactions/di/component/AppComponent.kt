package com.cba.transactions.di.component

import com.cba.transactions.CbaApp
import com.cba.transactions.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class,
        FragmentModule::class,
        RoomModule::class
    ]
)

interface AppComponent : AndroidInjector<CbaApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CbaApp): Builder

        fun build(): AppComponent
    }

}