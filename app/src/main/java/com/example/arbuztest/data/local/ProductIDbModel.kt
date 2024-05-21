package com.example.arbuztest.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "product")
@Parcelize
data class ProductIDbModel(
    @PrimaryKey
    @SerializedName("id")val id: Int,
    @SerializedName("basket_count")val basketCount: Int,
    @SerializedName("description") val description: String,
    @SerializedName("height")val height: String,
    @SerializedName("image_src")val imageSrc: String,
    @SerializedName("price")val price: Int,
    @SerializedName("size")val size: String,
    @SerializedName("title")val title: String,
    @SerializedName("video_src")val videoSrc: String
): Parcelable