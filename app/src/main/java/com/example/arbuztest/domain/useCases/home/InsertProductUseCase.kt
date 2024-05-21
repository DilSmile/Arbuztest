package com.example.arbuztest.domain.useCases.home

import com.example.arbuztest.data.local.ProductIDbModel

interface InsertProductUseCase {
    suspend fun insertProduct(product: ProductIDbModel)
}