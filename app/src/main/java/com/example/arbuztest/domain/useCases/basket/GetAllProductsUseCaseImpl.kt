package com.example.arbuztest.domain.useCases.basket

import com.example.arbuztest.data.local.ProductIDbModel
import com.example.arbuztest.domain.repository.BasketRepository
import javax.inject.Inject

class GetAllProductsUseCaseImpl @Inject constructor(
    private val repository: BasketRepository
): GetAllProductsUseCase {
    override suspend fun getAllProducts(): List<ProductIDbModel> {
        return repository.getAllProducts()
    }
}
