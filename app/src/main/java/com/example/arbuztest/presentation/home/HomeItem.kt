package com.example.arbuztest.presentation.home

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.arbuztest.R
import com.example.arbuztest.data.model.Product
import com.example.arbuztest.ui.theme.Brand900
import com.example.arbuztest.utils.Constant.IMAGE_URL
import com.example.arbuztest.utils.CustomButton
import com.example.arbuztest.utils.ShimmerBox
import com.example.arbuztest.utils.clickableWithoutRipple

@Composable
fun HomeItem(
    item: Product,
    buttonUiState: ButtonUiState,
    onBasketCountChange: (Int) -> Unit,
    basketCount: Int,
    onAddProductClick: () -> Unit,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .width(167.dp)
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier,
        ) {
            SubcomposeAsyncImage(
                model = "$IMAGE_URL${item.imageSrc}",
                contentScale = ContentScale.Crop,
                contentDescription = "item image",
                loading = {
                    ShimmerBox(
                        height = 100.dp
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.price.toString() + " тг",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = item.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))

                when (buttonUiState) {
                    ButtonUiState.DEFAULT -> {
                        CustomButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            text = stringResource(id = R.string.to_basket),
                            onButtonClick = onAddProductClick
                        )
                    }

                    ButtonUiState.IN_BASKET -> {
                        AddToBasketTypeBlock(
                            interactionSource = interactionSource,
                            onIncreaseClick = {
                                onIncreaseClick()
                                onBasketCountChange(basketCount+1)
                            },
                            onDecreaseClick = {
                                onDecreaseClick()
                                if(basketCount > 0) onBasketCountChange(basketCount - 1)
                            },
                            basketCount = basketCount
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))
                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    text = stringResource(id = R.string.delete_from_basket),
                    onButtonClick = onDeleteClick
                )
            }
        }
    }
}

@Composable
fun AddToBasketTypeBlock(
    interactionSource: MutableInteractionSource,
    basketCount: Int,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(2.dp, Brand900, RoundedCornerShape(50.dp))
            .clip(RoundedCornerShape(50.dp))
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .clickableWithoutRipple(
                        interactionSource,
                        onClick = { onDecreaseClick() }
                    )
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = "",
                tint = Brand900
            )
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = basketCount.toString(),
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp
            )
            Icon(
                modifier = Modifier
                    .clickableWithoutRipple(
                        interactionSource,
                        onClick = { onIncreaseClick() }
                    )
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "",
                tint = Brand900
            )
        }
    }
}
