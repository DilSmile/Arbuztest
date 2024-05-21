package com.example.arbuztest.domain.useCases.home

interface IncreaseProductCountUseCase {
    suspend fun increaseProductCount(productId: Int)
}