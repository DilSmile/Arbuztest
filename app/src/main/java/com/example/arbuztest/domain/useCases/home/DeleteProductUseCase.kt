package com.example.arbuztest.domain.useCases.home

interface DeleteProductUseCase {
    suspend fun deleteProduct(productId: Int)
}