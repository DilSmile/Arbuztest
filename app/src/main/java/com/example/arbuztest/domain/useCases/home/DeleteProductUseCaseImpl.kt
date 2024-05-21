package com.example.arbuztest.domain.useCases.home

import com.example.arbuztest.domain.repository.HomeRepository
import javax.inject.Inject

class DeleteProductUseCaseImpl @Inject constructor(
    val repository: HomeRepository
): DeleteProductUseCase {
    override suspend fun deleteProduct(productId: Int) {
        repository.deleteProduct(productId)
    }
}