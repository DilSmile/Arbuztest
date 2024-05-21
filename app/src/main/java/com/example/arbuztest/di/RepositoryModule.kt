package com.example.arbuztest.di

import com.example.arbuztest.data.repositoryImpl.BasketRepositoryImpl
import com.example.arbuztest.data.repositoryImpl.HomeRepositoryImpl
import com.example.arbuztest.domain.repository.BasketRepository
import com.example.arbuztest.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun provideHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    fun provideBasketRepository(impl: BasketRepositoryImpl): BasketRepository
}