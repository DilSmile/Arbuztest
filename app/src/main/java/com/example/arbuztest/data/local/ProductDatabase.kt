package com.example.arbuztest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductIDbModel::class], version = 1)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun basketDao(): ProductDao
}