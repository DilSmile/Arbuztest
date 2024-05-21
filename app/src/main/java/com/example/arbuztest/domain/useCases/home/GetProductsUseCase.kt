package com.example.arbuztest.domain.useCases.home

import com.example.arbuztest.data.model.ProductX

interface GetProductsUseCase {
    suspend fun getProducts(token: String): ProductX
}