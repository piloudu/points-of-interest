package com.example.pointsofinterest.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pointsofinterest.R

val Gothics = FontFamily(
    Font(R.font.gothics)
)

val RobotoLight = FontFamily(
    Font(R.font.roboto_light)
)

val RobotoMedium = FontFamily(
    Font(R.font.roboto_medium)
)

val RobotoRegular = FontFamily(
    Font(R.font.roboto_regular)
)

val RobotoBold = FontFamily(
    Font(R.font.roboto_bold)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)