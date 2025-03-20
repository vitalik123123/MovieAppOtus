package com.vitalik123.feature_details.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.vitalik123.ui_kit.theme.LocalColor

@Composable
fun Description(
    description: String,
    modifier: Modifier = Modifier
) {

    Text(
        modifier = modifier,
        text = description,
        color = LocalColor.current.textColorMode,
        fontSize = 16.sp
    )

}