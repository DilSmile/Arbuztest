package com.example.arbuztest.domain.useCases.home

interface GetBasketCountUseCase {
    suspend fun getBasketCount(productId: Int): Int
}