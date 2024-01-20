package com.example.dictionmaster.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dictionmaster.R

val RobotoCondensedFamily = FontFamily(
    Font(R.font.roboto_condensed_light, FontWeight.Light),
    Font(R.font.roboto_condensed_bold, FontWeight.Bold),
)
val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = RobotoCondensedFamily,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = RobotoCondensedFamily,
        fontWeight = FontWeight.Light,
        fontSize = 18.sp,
        lineHeight = 24.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = RobotoCondensedFamily,
        fontWeight = FontWeight.Light,
        fontSize = 22.sp,
        lineHeight = 20.sp,
    ),
)