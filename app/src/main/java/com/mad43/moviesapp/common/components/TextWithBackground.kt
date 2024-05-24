package com.mad43.moviesapp.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun TextWithBackGround(
    show: Boolean,
    text: String = "",
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White
) {
    if (show) {
        BoxWithCenterText(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = backgroundColor),
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16,
            textColor = textColor,
            fontFamily = FontFamily.SansSerif
        )
    }
}