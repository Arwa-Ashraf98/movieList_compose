package com.mad43.moviesapp.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextWithBackGround(show: Boolean, text: String = "") {
    if (show) {
        BoxWithCenterText(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.Red),
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16,
            textColor = Color.White,
            fontFamily = FontFamily.SansSerif
        )
    }
}