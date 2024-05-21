package com.example.arbuztest.di

import android.content.Context
import androidx.room.Room
import com.example.arbuztest.data.local.ProductDao
import com.example.arbuztest.data.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room
            .databaseBuilder(context,ProductDatabase::class.java,"product")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun getBasketDao(db: ProductDatabase): ProductDao = db.basketDao()
}