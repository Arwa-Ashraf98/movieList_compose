package com.mad43.moviesapp.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mad43.moviesapp.R


@Composable
fun ClickableImage(
    imageDrawable: Int,
    modifier: Modifier,
    action: () -> Unit
) {
    Image(
        painter = painterResource(id = imageDrawable),
        contentDescription = stringResource(id = R.string.app_name),
        modifier = modifier.clickable {
            action()
        }
    )
}