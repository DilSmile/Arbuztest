package com.example.arbuztest.data.repositoryImpl

import com.example.arbuztest.data.local.ProductDao
import com.example.arbuztest.data.local.ProductIDbModel
import com.example.arbuztest.domain.repository.BasketRepository
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val dao: ProductDao
): BasketRepository {
    override suspend fun getAllProducts(): List<ProductIDbModel> {
        return dao.getAllProducts()
    }
}