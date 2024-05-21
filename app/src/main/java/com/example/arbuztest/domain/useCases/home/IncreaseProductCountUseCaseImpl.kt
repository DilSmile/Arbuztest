package com.example.arbuztest.domain.useCases.home

import com.example.arbuztest.domain.repository.HomeRepository
import javax.inject.Inject

class IncreaseProductCountUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
): IncreaseProductCountUseCase {
    override suspend fun increaseProductCount(productId: Int) {
        repository.increaseProductCount(productId)
    }
}