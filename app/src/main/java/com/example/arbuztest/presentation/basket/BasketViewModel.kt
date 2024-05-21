package com.example.arbuztest.presentation.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arbuztest.data.mapper.ProductMapper
import com.example.arbuztest.data.model.Product
import com.example.arbuztest.domain.useCases.basket.GetAllProductsUseCase
import com.example.arbuztest.utils.toProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val mapper: ProductMapper
): ViewModel() {

    private val _basketProducts = MutableStateFlow<List<Product>>(emptyList())
    val basketProducts: StateFlow<List<Product>> get() = _basketProducts

    private val _basketItemCount = MutableStateFlow(0)
    val basketItemCount = _basketItemCount.asStateFlow()

    init {
        getAllProducts()
    }

     fun getAllProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            val productIDbModels = getAllProductsUseCase.getAllProducts()
            val productList = mapper.mapFromIDbModels(productIDbModels)
            _basketProducts.value = productList
            _basketItemCount.value = productList.size
        }
    }

    fun calculateTotalPrice(): Int {
        return _basketProducts.value.sumOf { it.price * it.basketCount }
    }

}