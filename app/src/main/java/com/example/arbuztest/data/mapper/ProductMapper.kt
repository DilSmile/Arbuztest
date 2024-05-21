package com.example.arbuztest.data.mapper

import com.example.arbuztest.data.local.ProductIDbModel
import com.example.arbuztest.data.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() {

    private fun ProductIDbModel.toProduct(): Product {
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


    fun Product.toProductIDbModel(): ProductIDbModel {
        return ProductIDbModel(
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

    fun mapFromIDbModels(productIDbModels: List<ProductIDbModel>): List<Product> {
        return productIDbModels.map { it.toProduct() }
    }
}