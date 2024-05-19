package com.mad43.moviesapp.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun BoxWithCenterText(
    modifier: Modifier,
    text: String,
    fontSize: Int,
    fontWeight: FontWeight,
    textColor: Color,
    fontFamily: FontFamily ,
    textModifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        RegularText(
            text = text,
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            modifier = textModifier,
            textStyle = TextStyle(color = textColor, fontFamily = fontFamily),
        )
    }
}