package com.mad43.moviesapp.common.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun MultiLineText(
    modifier: Modifier = Modifier,
    text: String,
    lineHeight: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = Color.Gray,
    fontSize: TextUnit = 16.sp
) {
    Text(
        text = text,
        color = color,
        fontWeight = fontWeight,
        fontSize = fontSize,
        modifier = modifier,
        maxLines = 5,
        lineHeight = lineHeight
    )
}