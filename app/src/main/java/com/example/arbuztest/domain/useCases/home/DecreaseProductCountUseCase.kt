package com.example.arbuztest.domain.useCases.home

interface DecreaseProductCountUseCase {
    suspend fun decreaseProductCount(productId: Int)
}