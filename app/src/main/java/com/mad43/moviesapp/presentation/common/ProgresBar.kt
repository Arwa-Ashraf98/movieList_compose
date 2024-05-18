package com.mad43.moviesapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun ProgressBar(
    boxModifier: Modifier = Modifier,
    isDisplayed: Boolean = false,
) {
    if (isDisplayed) {
        Box(
            modifier = boxModifier ,
        ) {
            CircularProgressIndicator(
                color = Color.Blue,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}