package com.example.arbuztest.presentation.home

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arbuztest.data.mapper.ProductMapper
import com.example.arbuztest.data.model.Product
import com.example.arbuztest.domain.useCases.home.DecreaseProductCountUseCase
import com.example.arbuztest.domain.useCases.home.DeleteProductUseCase
import com.example.arbuztest.domain.useCases.home.GetProductsUseCase
import com.example.arbuztest.domain.useCases.home.IncreaseProductCountUseCase
import com.example.arbuztest.domain.useCases.home.InsertProductUseCase
import com.example.arbuztest.utils.Constant.TOKEN
import com.example.arbuztest.utils.toProductIDbModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val insertProductUseCase: InsertProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val increaseProductCountUseCase: IncreaseProductCountUseCase,
    private val decreaseProductCountUseCase: DecreaseProductCountUseCase,
    private val mapper: ProductMapper,
    private val sharedPreferences: SharedPreferences,
): ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Initial)
    val homeState = _homeState.asStateFlow()

    private val _buttonUiState = MutableLiveData<Map<Int, ButtonUiState>>()
    val buttonUiState: LiveData<Map<Int, ButtonUiState>> = _buttonUiState

    private val _basketCounts = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val basketCounts: StateFlow<Map<Int, Int>> = _basketCounts

    init{
        getProducts()
        _buttonUiState.value = loadButtonUiState()
        _basketCounts.value = loadBasketCounts()

    }
    fun updateButtonUiState(productId: Int, state: ButtonUiState) {
        _buttonUiState.value = _buttonUiState.value?.toMutableMap()?.apply {
            put(productId, state)
        }
        saveButtonUiState(_buttonUiState.value ?: emptyMap())

    }

    fun updateBasketCount(productId: Int, count: Int) {
        _basketCounts.value = _basketCounts.value.toMutableMap().apply {
            put(productId, count)
        }
        saveBasketCounts(_basketCounts.value)
    }

    private fun loadButtonUiState(): Map<Int, ButtonUiState> {
        val allEntries = sharedPreferences.all
        val buttonStates = mutableMapOf<Int, ButtonUiState>()
        for ((key, value) in allEntries) {
            if (key.startsWith("button_state_")) {
                val productId = key.removePrefix("button_state_").toInt()
                val state = ButtonUiState.valueOf(value as String)
                buttonStates[productId] = state
            }
        }
        return buttonStates
    }

    private fun loadBasketCounts(): Map<Int, Int> {
        val allEntries = sharedPreferences.all
        val basketCounts = mutableMapOf<Int, Int>()
        for ((key, value) in allEntries) {
            if (key.startsWith("basket_count_")) {
                val productId = key.removePrefix("basket_count_").toInt()
                val count = value as Int
                basketCounts[productId] = count
            }
        }
        return basketCounts
    }
    private fun saveButtonUiState(state: Map<Int, ButtonUiState>) {
        val editor = sharedPreferences.edit()
        for ((productId, buttonState) in state) {
            editor.putString("button_state_$productId", buttonState.name)
        }
        editor.apply()
    }

    private fun saveBasketCounts(counts: Map<Int, Int>) {
        val editor = sharedPreferences.edit()
        for ((productId, count) in counts) {
            editor.putInt("basket_count_$productId", count)
        }
        editor.apply()
    }

     fun getProducts() {
        _homeState.value = HomeState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getProductsUseCase.getProducts(TOKEN)
                _homeState.update { HomeState.Products(result) }
                Log.d("CatalogViewModel", "getProducts: $result")
            } catch (e: Exception) {
                _homeState.update { HomeState.Failure(e.message.toString()) }
                Log.d("CatalogViewModel", "getProducts error ${e.message.toString()}")
            }
        }
    }

    fun addProduct(product: Product){
        viewModelScope.launch {
            val productIDbModel = product.toProductIDbModel()
            insertProductUseCase.insertProduct(productIDbModel)
        }
    }

    fun increaseProductCount(productId: Int){
        viewModelScope.launch {
            increaseProductCountUseCase.increaseProductCount(productId)
        }
    }
    fun decreaseProductCount(productId: Int){
        viewModelScope.launch {
            decreaseProductCountUseCase.decreaseProductCount(productId)
        }
    }
    fun deleteProduct(productId: Int){
        viewModelScope.launch {
            deleteProductUseCase.deleteProduct(productId)
        }
    }

}