package com.cba.transactions.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cba.transactions.R
import com.cba.transactions.data.ShoppingRepository
import com.cba.transactions.data.ShoppingRepositoryImp
import com.cba.transactions.data.local.ShoppingDatabase
import com.cba.transactions.data.local.dao.ShoppingDao
import com.cba.transactions.data.remote.ShoppingService
import com.cba.transactions.data.remote.ShoppingService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(@ApplicationContext context: Context): ShoppingDatabase {
        return Room.databaseBuilder(context, ShoppingDatabase::class.java, ShoppingDatabase.DATABASE).build()
    }


    @Singleton
    @Provides
    fun provideShoppingRepository(
        dao: ShoppingDao,
        api:ShoppingService
    ) = ShoppingRepositoryImp(dao, api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): ShoppingService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ShoppingService::class.java)
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )
}