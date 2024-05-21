package com.example.arbuztest.data.remote

import com.example.arbuztest.data.model.ProductX
import retrofit2.http.GET
import retrofit2.http.Header

interface ArbuzTestApi {

    @GET("products")
    suspend fun getProducts(
        @Header("Authorization") token: String
    ): ProductX
}
