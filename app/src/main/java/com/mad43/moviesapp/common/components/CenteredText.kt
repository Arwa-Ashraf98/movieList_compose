package com.mad43.moviesapp.common.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CenteredText(
    text: String,
    textColor: Color,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.SansSerif,
    fontSize: Int,
    modifier: Modifier
) {
    BoxWithCenterText(
        modifier = modifier,
        text = text,
        fontWeight = fontWeight,
        fontSize = fontSize,
        textColor = textColor,
        fontFamily = fontFamily
    )
}