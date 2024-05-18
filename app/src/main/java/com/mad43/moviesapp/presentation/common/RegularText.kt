package com.mad43.moviesapp.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit


@Composable
fun RegularText(
    modifier: Modifier = Modifier,
    text: String,
    fontWeight: FontWeight = FontWeight.SemiBold,
    color: Color = Color.Black,
    fontSize: TextUnit ,
) {
    Text(
        text = text,
        color = color,
        fontWeight = fontWeight,
        fontSize = fontSize,
        modifier = modifier ,
        maxLines = 1
    )
}