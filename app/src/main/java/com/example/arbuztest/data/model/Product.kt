package com.example.arbuztest.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Product(
    @SerializedName("id")val id: Int,
    @SerializedName("basket_count")val basketCount: Int,
    @SerializedName("description") val description: String,
    @SerializedName("height")val height: String,
    @SerializedName("image_src")val imageSrc: String,
    @SerializedName("price")val price: Int,
    @SerializedName("size")val size: String,
    @SerializedName("title")val title: String,
    @SerializedName("video_src")val videoSrc: String
)