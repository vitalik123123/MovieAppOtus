package com.vitalik123.feature_home.presentation.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitalik123.core.R
import com.vitalik123.core.utils.TextApp
import com.vitalik123.ui_kit.theme.LocalColor

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    onClickWatchlist: () -> Unit = {},
    onClickRatelist: () -> Unit = {},
    onClickSearch: () -> Unit = {}
) {

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        TitleUserName(
            Modifier
                .padding(12.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
        )

        IconTopBar(
            modifier = Modifier.padding(vertical = 12.dp),
            paint = R.drawable.ic_star_full,
            tint = LocalColor.current.redMain,
            onClick = onClickRatelist
        )

        IconTopBar(
            modifier = Modifier.padding(vertical = 12.dp),
            paint = R.drawable.ic_bookmark_full,
            tint = LocalColor.current.redMain,
            onClick = onClickWatchlist
        )

        IconTopBar(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .padding(end = 12.dp),
            paint = R.drawable.ic_search,
            tint = LocalColor.current.redMain,
            onClick = onClickSearch
        )
    }
}

@Composable
private fun TitleUserName(
    modifier: Modifier = Modifier
) {
    Text(
        text = TextApp.USER_NAME,
        fontSize = 24.sp,
        modifier = modifier,
        color = LocalColor.current.textColorMode
    )
}


@Composable
private fun IconTopBar(
    paint: Int,
    tint: Color = LocalColor.current.blackAlways,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {

    IconButton(onClick = onClick, modifier = modifier) {

        Icon(
            modifier = Modifier.size(27.dp),
            painter = painterResource(paint),
            contentDescription = null,
            tint = tint
        )
    }
}