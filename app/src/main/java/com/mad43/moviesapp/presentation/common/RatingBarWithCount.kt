package com.mad43.moviesapp.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RatingBarWithCount(vote : Double , modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
    ) {
        RatingBar(
            starsModifier = Modifier.size(18.dp),
            rating = vote / 2
        )

        RegularText(
            modifier = Modifier.padding(start = 4.dp),
            text = vote.toString().take(3),
            color = Color.LightGray,
            fontSize = 14.sp
        )
    }
}