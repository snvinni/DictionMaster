package com.example.dictionmaster.ui.theme

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
    bodyLarge = TextStyle(
        fontFamily = RobotoCondensedFamily,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = RobotoCondensedFamily,
        fontWeight = FontWeight.Light,
        fontSize = 22.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
)