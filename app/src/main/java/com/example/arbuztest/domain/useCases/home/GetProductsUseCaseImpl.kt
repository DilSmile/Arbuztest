package com.example.arbuztest.domain.useCases.home

import com.example.arbuztest.data.model.ProductX
import com.example.arbuztest.domain.repository.HomeRepository
import javax.inject.Inject

class GetProductsUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
): GetProductsUseCase {
    override suspend fun getProducts(token: String): ProductX {
        return repository.getProducts(token)
    }
}