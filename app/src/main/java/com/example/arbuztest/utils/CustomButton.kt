package com.example.arbuztest.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arbuztest.ui.theme.Brand900

@Composable
fun CustomButton(
    text: String,
    enabled: Boolean = true,
    textColor: Color = Color.White,
    backgroundColor: Color = Brand900,
    borderColor: Color = Brand900,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(50.dp)

    Button(
        modifier = modifier,
        onClick = { onButtonClick() },
        colors = ButtonColors(
            containerColor = backgroundColor,
            contentColor = Color.White,
            disabledContainerColor = backgroundColor,
            disabledContentColor = Color.White
        ),
        shape = shape,
        enabled = enabled,
        border = BorderStroke(2.dp, borderColor)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 13.sp
        )
    }
}
