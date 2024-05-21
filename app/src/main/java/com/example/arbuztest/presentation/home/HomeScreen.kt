package com.example.arbuztest.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.arbuztest.R
import com.example.arbuztest.data.model.Product
import com.example.arbuztest.utils.ProgressBlock


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    val homeState = viewModel.homeState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = stringResource(id = R.string.home),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(all = 16.dp)
        ) {
            when (val currentState = homeState.value) {
                is HomeState.Loading -> {
                    ProgressBlock()
                }

                is HomeState.Failure -> {
                    Log.d("CatalogScreen", "error ${currentState.message}")
                }

                is HomeState.Products -> {

                    HomeScreenContent(
                        list = currentState.products,
                        onAddProductClick = {
                            viewModel.addProduct(it)
                        },
                        onIncreaseClick = {
                            viewModel.increaseProductCount(it.id)
                        },
                        onDecreaseClick = {
                            viewModel.decreaseProductCount(it.id)
                        },
                        onDeleteClick = {
                            viewModel.deleteProduct(it.id)
                        },
                        viewModel = viewModel
                    )
                }

                is HomeState.Initial -> {

                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    list: List<Product>,
    onAddProductClick: (Product) -> Unit,
    onIncreaseClick: (Product) -> Unit,
    onDecreaseClick: (Product) -> Unit,
    onDeleteClick: (Product) -> Unit,
    viewModel: HomeViewModel,
) {

    GridListType(
        list = list,
        onAddProductClick = {
            onAddProductClick(it)
        },
        onIncreaseClick = {
            onIncreaseClick(it)
        },
        onDecreaseClick = { onDecreaseClick(it) },
        onDeleteClick = {
            onDeleteClick(it)
        },
        viewModel = viewModel
    )
}

@Composable
fun GridListType(
    list: List<Product>,
    onAddProductClick: (Product) -> Unit,
    onIncreaseClick: (Product) -> Unit,
    onDecreaseClick: (Product) -> Unit,
    onDeleteClick: (Product) -> Unit,
    viewModel: HomeViewModel,
) {
    val buttonUiState by viewModel.buttonUiState.observeAsState(emptyMap())
    val basketCounts by viewModel.basketCounts.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        items(items = list) { item ->
            HomeItem(
                item = item,
                onAddProductClick = {
                    onAddProductClick(item)
                    viewModel.updateButtonUiState(item.id, ButtonUiState.IN_BASKET)
                    viewModel.updateBasketCount(item.id, 0)
                },
                onIncreaseClick = {
                    onIncreaseClick(item)
                    viewModel.updateBasketCount(item.id, basketCounts[item.id] ?: (0 + 1))
                },
                onDecreaseClick = {
                    onDecreaseClick(item)
                    viewModel.updateBasketCount(
                        item.id,
                        (basketCounts[item.id] ?: 0).coerceAtLeast(1) - 1
                    )
                },
                onDeleteClick = {
                    onDeleteClick(item)
                    viewModel.updateButtonUiState(item.id, ButtonUiState.DEFAULT)
                    viewModel.updateBasketCount(item.id, 0)
                },
                buttonUiState = buttonUiState[item.id] ?: ButtonUiState.DEFAULT,
                onBasketCountChange = {
                    viewModel.updateBasketCount(item.id, it)
                },
                basketCount = basketCounts[item.id] ?: 0,
            )
        }
    }
}