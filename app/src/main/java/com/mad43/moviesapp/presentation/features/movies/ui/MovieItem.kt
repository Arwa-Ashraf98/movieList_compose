package com.mad43.moviesapp.presentation.features.movies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.mad43.moviesapp.R
import com.mad43.moviesapp.common.components.MultiLineText
import com.mad43.moviesapp.common.components.RatingBarWithCount
import com.mad43.moviesapp.common.components.RegularText
import com.mad43.moviesapp.common.components.VerticalSpacer
import com.mad43.moviesapp.presentation.models.DisplayedMovie
import com.mad43.moviesapp.common.utlis.Constants
import com.mad43.moviesapp.common.components.getAverageColor


@Composable
fun MovieItem(movie: DisplayedMovie, action: (id: Int) -> Unit) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Constants.BASE_IMAGE_URL + movie.imageUrl)
            .size(Size.ORIGINAL)
            .build()
    ).state

    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondaryContainer,
                        dominantColor
                    )
                )
            )
            .clickable {
                action.invoke(movie.movieId)
            }
    ) {
        if (imageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = movie.title
                )
            }
        }

        if (imageState is AsyncImagePainter.State.Success) {
            dominantColor = getAverageColor(
                imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
            )

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(22.dp)),
                painter = imageState.painter,
                contentDescription = movie.title,
                contentScale = ContentScale.FillBounds
            )
        }

        VerticalSpacer(6)
        MultiLineText(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp),
            text = movie.title,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp
        )
        RatingBarWithCount(vote = movie.vote , modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 8.dp, top = 4.dp))

        RegularText(
            modifier = Modifier.padding(start = 16.dp, end = 8.dp , bottom = 8.dp),
            text = stringResource(id = R.string.release_date).plus(": ").plus(movie.date),
            color = Color.Black,
            fontSize = 15.sp
        )
        VerticalSpacer(8)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieItem(movie = DisplayedMovie(), action = {})
}


