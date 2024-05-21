package com.example.arbuztest.domain.repository

import com.example.arbuztest.data.local.ProductIDbModel
import com.example.arbuztest.data.model.ProductX

interface HomeRepository {
    suspend fun getProducts(token: String): ProductX

    suspend fun addProduct(product: ProductIDbModel)

    suspend fun deleteProduct(productId: Int)

    suspend fun increaseProductCount(productId: Int)

    suspend fun decreaseProductCount(productId: Int)

    suspend fun getBasketCount(productId: Int): Int
}