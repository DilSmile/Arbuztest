package com.example.arbuztest.domain.useCases.home

import com.example.arbuztest.domain.repository.HomeRepository
import javax.inject.Inject

class GetBasketCountUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
): GetBasketCountUseCase {
    override suspend fun getBasketCount(productId: Int): Int {
        return repository.getBasketCount(productId)
    }
}