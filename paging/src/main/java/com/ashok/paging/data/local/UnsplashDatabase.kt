package com.ashok.paging.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashok.paging.data.local.dao.UnsplashImageDao
import com.ashok.paging.data.local.dao.UnsplashRemoteKeysDao
import com.ashok.paging.modal.UnsplashImage
import com.ashok.paging.modal.UnsplashRemoteKeys

@Database(entities = [UnsplashImage::class, UnsplashRemoteKeys::class], version = 1)
abstract class UnsplashDatabase : RoomDatabase() {
    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao

}