package com.mad43.moviesapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.mad43.moviesapp.R
import com.mad43.moviesapp.utlis.Constants


@Composable
fun MovieAsyncImage(
    modifier: Modifier,
    imageUrl: String,
    crossFade: Boolean = true
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Constants.BASE_IMAGE_URL.plus(imageUrl))
            .crossfade(crossFade)
            .size(Size.ORIGINAL)
            .build(),
        contentDescription = stringResource(id = R.string.app_name),
        placeholder = painterResource(id = R.drawable.loading),
        contentScale = ContentScale.FillBounds,
        modifier = modifier,
        error = painterResource(id = R.drawable.image_error)
        )
}