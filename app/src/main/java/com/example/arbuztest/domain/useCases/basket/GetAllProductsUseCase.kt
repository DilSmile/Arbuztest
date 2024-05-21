package com.example.arbuztest.domain.useCases.basket

import com.example.arbuztest.data.local.ProductIDbModel

interface GetAllProductsUseCase {
    suspend fun getAllProducts(): List<ProductIDbModel>
}