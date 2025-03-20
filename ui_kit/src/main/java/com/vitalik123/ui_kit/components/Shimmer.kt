package com.vitalik123.ui_kit.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowHomeShimmer() {

    Row {
        repeat(10) {
            val shimmerColors = listOf(
                Color.LightGray.copy(.6f),
                Color.LightGray.copy(.2f),
                Color.LightGray.copy(.6f)
            )

            val transition = rememberInfiniteTransition(label = "")
            val translateAnim = transition.animateFloat(
                initialValue = 0f,
                targetValue = 1000f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        easing = FastOutSlowInEasing
                    ),
                    repeatMode = RepeatMode.Reverse
                ), label = ""
            )

            val brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset.Zero,
                end = Offset(x = translateAnim.value, y = translateAnim.value)
            )

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .width(170.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Spacer(
                    modifier = Modifier
                        .size(170.dp, 230.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(brush)
                )

                Spacer(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(170.dp, 20.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(brush)
                )
            }
        }
    }
}