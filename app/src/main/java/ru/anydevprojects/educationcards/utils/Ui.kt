package ru.anydevprojects.educationcards.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun pixelsToDp(pixels: Int): Dp {
    return with(LocalDensity.current) {
        Dp(pixels / density)
    }
}
