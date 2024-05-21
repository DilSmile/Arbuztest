package com.example.arbuztest.domain.repository

import com.example.arbuztest.data.local.ProductIDbModel

interface BasketRepository {
    suspend fun getAllProducts(): List<ProductIDbModel>
}