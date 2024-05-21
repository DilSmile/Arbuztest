package com.example.arbuztest.data.repositoryImpl

import com.example.arbuztest.data.local.ProductDao
import com.example.arbuztest.data.local.ProductIDbModel
import com.example.arbuztest.data.remote.ArbuzTestApi
import com.example.arbuztest.data.model.ProductX
import com.example.arbuztest.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: ArbuzTestApi,
    private val dao: ProductDao
) : HomeRepository {
    override suspend fun getProducts(token: String): ProductX {
        return api.getProducts(getToken(token))
    }

    override suspend fun addProduct(product: ProductIDbModel) {
        dao.addProduct(product)
    }

    override suspend fun deleteProduct(productId: Int) {
        dao.deleteProduct(productId)
    }

    override suspend fun increaseProductCount(productId: Int) {
        dao.increaseProductCount(productId)
    }

    override suspend fun decreaseProductCount(productId: Int) {
        dao.decreaseProductCount(productId)
    }

    override suspend fun getBasketCount(productId: Int): Int {
        return dao.getBasketCount(productId)
    }

    private fun getToken(token: String): String {
        return "Bearer $token"
    }
}