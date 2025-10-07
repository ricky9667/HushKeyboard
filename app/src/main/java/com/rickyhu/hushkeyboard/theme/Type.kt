package com.rickyhu.hushkeyboard.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rickyhu.hushkeyboard.R

val FontRubik =
    FontFamily(
        Font(
            R.font.rubik_regular,
            style = FontStyle.Normal,
        ),
        Font(
            R.font.rubik_italic,
            style = FontStyle.Italic,
        ),
    )

val FontMontserrat =
    FontFamily(
        Font(
            R.font.montserrat_regular,
            style = FontStyle.Normal,
        ),
        Font(
            R.font.montserrat_italic,
            style = FontStyle.Italic,
        ),
    )

val Typography =
    Typography(
        displayLarge =
            TextStyle(
                fontFamily = FontRubik,
                fontWeight = FontWeight.Bold,
                fontSize = 60.sp,
            ),
        displayMedium =
            TextStyle(
                fontFamily = FontRubik,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
            ),
        displaySmall =
            TextStyle(
                fontFamily = FontRubik,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            ),
        titleLarge =
            TextStyle(
                fontFamily = FontMontserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                lineHeight = 36.sp,
            ),
        titleMedium =
            TextStyle(
                fontFamily = FontMontserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                lineHeight = 28.sp,
            ),
        titleSmall =
            TextStyle(
                fontFamily = FontMontserrat,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                lineHeight = 24.sp,
            ),
        bodyLarge =
            TextStyle(
                fontFamily = FontMontserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 24.sp,
            ),
        bodyMedium =
            TextStyle(
                fontFamily = FontMontserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                lineHeight = 20.sp,
            ),
        bodySmall =
            TextStyle(
                fontFamily = FontMontserrat,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                lineHeight = 20.sp,
            ),
    )
