package com.example.arbuztest.utils

import com.example.arbuztest.data.local.ProductIDbModel
import com.example.arbuztest.data.model.Product

fun ProductIDbModel.toProduct(): Product {
    return Product(
        id = this.id,
        basketCount = this.basketCount,
        description = this.description,
        height = this.height,
        imageSrc = this.imageSrc,
        price = this.price,
        size = this.size,
        title = this.title,
        videoSrc = this.videoSrc
    )
}
