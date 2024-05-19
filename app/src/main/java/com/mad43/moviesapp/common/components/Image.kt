package com.mad43.moviesapp.common.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mad43.moviesapp.R

@Composable
fun LargeImage(
    modifier: Modifier,
    imageResource: Int,
    contentScale: ContentScale = ContentScale.FillBounds
) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = stringResource(id = R.string.app_name),
        modifier = modifier,
        contentScale = contentScale
    )
}