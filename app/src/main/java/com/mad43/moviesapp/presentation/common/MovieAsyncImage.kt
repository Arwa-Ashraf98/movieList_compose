package com.mad43.moviesapp.presentation.common

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.size.Size
import com.mad43.moviesapp.R
import com.mad43.moviesapp.utlis.Constants
import kotlinx.coroutines.Dispatchers


@Composable
fun MovieAsyncImage(
    modifier: Modifier,
    imageUrl: String,
    crossFade: Boolean = true
) {
    val listener = object : ImageRequest.Listener {
        override fun onError(request: ImageRequest, result: ErrorResult) {
            super.onError(request, result)
            Log.d("TAG", "onError: ${result.throwable}")
        }

        override fun onSuccess(request: ImageRequest, result: SuccessResult) {
            super.onSuccess(request, result)
            Log.e("TAG", "onSuccess: ${result.diskCacheKey}" )
        }
    }

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(Constants.BASE_IMAGE_URL.plus(imageUrl))
        .listener(listener)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .placeholder(R.drawable.loading)
        .error(R.drawable.image_error)
        .crossfade(crossFade)
        .size(Size.ORIGINAL)
        .fallback(R.drawable.image_error)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = stringResource(id = R.string.app_name),
        contentScale = ContentScale.FillBounds,
        modifier = modifier,
        )
}