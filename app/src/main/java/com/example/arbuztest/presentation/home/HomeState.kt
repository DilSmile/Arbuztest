package com.example.arbuztest.presentation.home

import com.example.arbuztest.data.model.ProductX

sealed interface HomeState {
    object Initial : HomeState
    object Loading : HomeState
    data class Failure(val message: String) : HomeState
    data class Products(val products: ProductX) : HomeState
}