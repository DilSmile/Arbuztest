package com.example.arbuztest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: ProductIDbModel)

    @Query("UPDATE product SET basketCount = basketCount + 1 WHERE id = :productId")
    suspend fun increaseProductCount(productId: Int)

    @Query("UPDATE product SET basketCount = basketCount - 1 WHERE id = :productId AND basketCount > 0")
    suspend fun decreaseProductCount(productId: Int)

    @Query("DELETE FROM product WHERE id = :productId")
    suspend fun deleteProduct(productId: Int)

    @Query("DELETE FROM product")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM product")
    suspend fun getAllProducts(): List<ProductIDbModel>

    @Query("SELECT COUNT(*) FROM product WHERE id = :productId")
    suspend fun getBasketCount(productId: Int): Int
}