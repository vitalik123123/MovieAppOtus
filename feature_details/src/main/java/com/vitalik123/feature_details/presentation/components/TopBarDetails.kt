package com.vitalik123.feature_details.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.vitalik123.core.R
import com.vitalik123.core.utils.TextApp
import com.vitalik123.ui_kit.theme.LocalColor

@Composable
fun TopBarDetails(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(LocalColor.current.backgroundMode)
    ) {

        IconButton(onBackClick) {
            Icon(painter = painterResource(R.drawable.ic_back), contentDescription = null)
        }

        Text(
            text = TextApp.DESCRIPTION,
            color = LocalColor.current.textColorMode,
            fontSize = 28.sp,
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            textAlign = TextAlign.Center
        )

    }
}