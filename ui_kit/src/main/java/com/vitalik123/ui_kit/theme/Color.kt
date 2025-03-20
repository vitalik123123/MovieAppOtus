package com.vitalik123.ui_kit.theme

import androidx.compose.ui.graphics.Color

object AppColors {
    val black = Color(0xFF000000)
    val white = Color(0xFFFFFFFF)
    val red_more = Color(0xFFDC0303)
    val kinopoisk_orange = Color(0xFFF89B09)
}

data class ColorPalette(
    val textColorMode: Color,
    val backgroundMode: Color,
    val redMain: Color,
    val whiteAlways: Color,
    val blackAlways: Color,
    val kinopoiskOrange: Color
)

fun lightColorPalette(): ColorPalette = ColorPalette(
    textColorMode = AppColors.black,
    backgroundMode = AppColors.white,
    redMain = AppColors.red_more,
    whiteAlways = AppColors.white,
    blackAlways = AppColors.black,
    kinopoiskOrange = AppColors.kinopoisk_orange
)

fun darkColorPalette(): ColorPalette = ColorPalette(
    textColorMode = AppColors.white,
    backgroundMode = AppColors.black,
    redMain = AppColors.red_more,
    whiteAlways = AppColors.white,
    blackAlways = AppColors.black,
    kinopoiskOrange = AppColors.kinopoisk_orange
)