package com.mad43.moviesapp.presentation.features.movies.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mad43.moviesapp.R
import com.mad43.moviesapp.common.components.CenteredText
import com.mad43.moviesapp.common.components.LargeImage
import com.mad43.moviesapp.common.components.VerticalSpacer


@Composable
fun ErrorMainScreen(modifier: Modifier) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(top = 156.dp, bottom = 24.dp)
            ,
            contentAlignment = Alignment.Center
        ) {
            LargeImage(
                modifier = Modifier
                    .fillMaxSize(),
                imageResource = R.drawable.no_wifi,
                contentScale = ContentScale.Fit
            )
        }
        VerticalSpacer(size = 16)
        CenteredText(
            text = stringResource(id = R.string.no_network),
            textColor = Color.Black,
            fontSize = 16,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )
    }
}