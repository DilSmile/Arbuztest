package com.example.arbuztest.di

import com.example.arbuztest.domain.repository.BasketRepository
import com.example.arbuztest.domain.repository.HomeRepository
import com.example.arbuztest.domain.useCases.basket.GetAllProductsUseCase
import com.example.arbuztest.domain.useCases.basket.GetAllProductsUseCaseImpl
import com.example.arbuztest.domain.useCases.home.DecreaseProductCountUseCase
import com.example.arbuztest.domain.useCases.home.DecreaseProductCountUseCaseImpl
import com.example.arbuztest.domain.useCases.home.DeleteProductUseCase
import com.example.arbuztest.domain.useCases.home.DeleteProductUseCaseImpl
import com.example.arbuztest.domain.useCases.home.GetBasketCountUseCase
import com.example.arbuztest.domain.useCases.home.GetBasketCountUseCaseImpl
import com.example.arbuztest.domain.useCases.home.GetProductsUseCase
import com.example.arbuztest.domain.useCases.home.GetProductsUseCaseImpl
import com.example.arbuztest.domain.useCases.home.IncreaseProductCountUseCase
import com.example.arbuztest.domain.useCases.home.IncreaseProductCountUseCaseImpl
import com.example.arbuztest.domain.useCases.home.InsertProductUseCase
import com.example.arbuztest.domain.useCases.home.InsertProductUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    fun provideProductUseCase(repo: HomeRepository): GetProductsUseCase =
        GetProductsUseCaseImpl(repo)

    @Provides
    fun provideInsertProductUseCase(repo: HomeRepository): InsertProductUseCase =
        InsertProductUseCaseImpl(repo)

    @Provides
    fun provideDeleteProductUseCase(repo: HomeRepository): DeleteProductUseCase =
        DeleteProductUseCaseImpl(repo)

    @Provides
    fun provideIncreaseProductCountUseCase(repo: HomeRepository): IncreaseProductCountUseCase =
        IncreaseProductCountUseCaseImpl(repo)

    @Provides
    fun provideDecreaseProductCountUseCase(repo: HomeRepository): DecreaseProductCountUseCase =
        DecreaseProductCountUseCaseImpl(repo)

    @Provides
    fun provideGetAllProductsUseCase(repo: BasketRepository): GetAllProductsUseCase =
        GetAllProductsUseCaseImpl(repo)

    @Provides
    fun provideGetBasketCountUseCase(repo: HomeRepository): GetBasketCountUseCase =
        GetBasketCountUseCaseImpl(repo)
}