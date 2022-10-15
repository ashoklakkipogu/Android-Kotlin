package com.ashok.paging.modal
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ashok.paging.utils.Constants.UNSPLASH_IMAGE_TABLE
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class UnsplashImage(
    @PrimaryKey(autoGenerate = false)
    val id:String,
    @Embedded
    val urls:Url,
    val likes:Int,
    @Embedded
    val user: User
)
