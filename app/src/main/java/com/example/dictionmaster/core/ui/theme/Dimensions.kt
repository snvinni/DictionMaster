package com.example.dictionmaster.core.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalDimensions = compositionLocalOf { Dimensions() }

data class Dimensions(
    val huge: Dp = 64.dp,
    val big: Dp = 38.dp,
    val semiBig: Dp = 30.dp,
    val large: Dp = 28.dp,
    val medium: Dp = 22.dp,
    val default: Dp = 16.dp,
    val semiMedium: Dp = 12.dp,
    val small: Dp = 8.dp,
)