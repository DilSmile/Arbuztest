package com.example.arbuztest.di

import com.example.arbuztest.data.mapper.ProductMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductMapper(): ProductMapper {
        return ProductMapper()
    }
}