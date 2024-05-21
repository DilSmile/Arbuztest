package com.example.arbuztest.domain.useCases.home

import com.example.arbuztest.data.local.ProductIDbModel
import com.example.arbuztest.domain.repository.HomeRepository
import javax.inject.Inject

class InsertProductUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
): InsertProductUseCase {
    override suspend fun insertProduct(product: ProductIDbModel) {
        return repository.addProduct(product)
    }
}