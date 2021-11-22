package com.palaspro.citiboxchallenge.presenterlayer.base

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val margin4Dp = 4.dp
val margin8Dp = 8.dp
val margin16Dp = 16.dp

val heightItem = 144.dp
val nameCharacterItem = 72.dp

val fontSizeTitleToolbar = 18.sp

val Yellow = Color(0xFFFAFD7C)
val YellowLight = Color(0xFFFAE48B)
val Brown = Color(0xFF82491E)
val BlueDark = Color(0xFF24325F)
val BlueMediumDark = Color(0xFF69C8EC)
val BlueMediumLight = Color(0xFFA6EEE6)
val BlueLight = Color(0xFFB7E4F9)
val Red = Color(0xFFFB6467)
val Green = Color(0xFF526E2F)
val Pink = Color(0xFFE762D7)
val Orange = Color(0xFFE89242)

val themeColors = lightColors(
    primary = BlueDark,
    primaryVariant = Yellow,
    secondary = Brown,
    secondaryVariant = Pink,
    background = BlueLight,
    onError = Red
)
