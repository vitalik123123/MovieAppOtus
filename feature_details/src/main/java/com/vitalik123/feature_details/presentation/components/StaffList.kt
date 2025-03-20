package com.vitalik123.feature_details.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vitalik123.feature_details.dto.StaffUi
import com.vitalik123.ui_kit.theme.LocalColor

@Composable
fun StaffList(
    list: List<StaffUi>,
    modifier: Modifier = Modifier
) {

    LazyRow(
        modifier = modifier
    ) {
        items(list) {
            StaffItem(it)
        }
    }
}

@Composable
private fun StaffItem(
    staff: StaffUi,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
    ) {

        AsyncImage(
            contentScale = ContentScale.Crop,
            model = staff.poster, contentDescription = null,
            modifier = Modifier
                .padding(6.dp)
                .clip(CircleShape)
                .size(48.dp)
        )

        Column(
            modifier = Modifier.padding(6.dp)
        ) {

            Text(text = staff.name, color = LocalColor.current.textColorMode, fontSize = 12.sp)

            Text(
                text = staff.role,
                color = LocalColor.current.textColorMode,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

        }
    }
}