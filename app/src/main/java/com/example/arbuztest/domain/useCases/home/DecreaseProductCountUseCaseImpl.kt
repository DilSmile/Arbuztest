package com.example.arbuztest.domain.useCases.home

import com.example.arbuztest.domain.repository.HomeRepository
import javax.inject.Inject

class DecreaseProductCountUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
): DecreaseProductCountUseCase {
    override suspend fun decreaseProductCount(productId: Int) {
        repository.decreaseProductCount(productId)
    }
}