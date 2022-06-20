package com.cba.transactions.di.module

import android.app.Application
import androidx.room.Room
import com.cba.transactions.data.local.CbaDatabase
import com.cba.transactions.data.local.dao.CbaDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDataBase(application: Application): CbaDatabase =
        Room.databaseBuilder(application, CbaDatabase::class.java, CbaDatabase.DATABASE)
            .allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideNotificationDao(cbaDatabase: CbaDatabase): CbaDao =
        cbaDatabase.cbaDao()


}