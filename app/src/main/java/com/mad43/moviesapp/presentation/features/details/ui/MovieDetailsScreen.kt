package com.mad43.moviesapp.presentation.features.details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mad43.moviesapp.R
import com.mad43.moviesapp.common.components.ClickableImage
import com.mad43.moviesapp.common.components.HorizontalSpacer
import com.mad43.moviesapp.common.components.MovieAsyncImage
import com.mad43.moviesapp.common.components.MultiLineText
import com.mad43.moviesapp.common.components.ProgressBar
import com.mad43.moviesapp.common.components.RatingBarWithCount
import com.mad43.moviesapp.common.components.RegularText
import com.mad43.moviesapp.common.components.VerticalSpacer
import com.mad43.moviesapp.presentation.features.details.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(navController: NavController) {
    val detailsViewModel = hiltViewModel<MovieDetailsViewModel>()
    val detailsState = detailsViewModel.detailsState.collectAsState().value

    if (detailsState.isLoading) {
        ProgressBar(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            isDisplayed = true
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            VerticalSpacer(size = 8)
            ClickableImage(
                imageDrawable = R.drawable.baseline_arrow_back_24,
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp)
                    .height(25.dp)
                    .width(25.dp)
            ) {
                navController.popBackStack()
            }
            MovieAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                imageUrl = detailsState.movie?.backImageUrl ?: ""
            )

            VerticalSpacer(size = 8)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                MovieAsyncImage(
                    imageUrl = detailsState.movie?.imageUrl ?: "",
                    modifier = Modifier
                        .width(120.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                )
                HorizontalSpacer(size = 4)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    MultiLineText(
                        fontWeight = FontWeight.Bold,
                        text = detailsState.movie?.title ?: "",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )
                    RatingBarWithCount(vote = detailsState.movie?.voteAverage?: 0.0 , modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 12.dp, top = 4.dp))
                    VerticalSpacer(size = 8)
                    RegularText(
                        fontWeight = FontWeight.Normal,
                        text = stringResource(id = R.string.lang).plus(": ")
                            .plus(detailsState.movie?.language),
                        modifier = Modifier.padding(start = 6.dp),
                        fontSize = 14.sp
                    )
                    VerticalSpacer(10)
                    RegularText(
                        modifier = Modifier.padding(start = 6.dp),
                        fontWeight = FontWeight.Normal,
                        text = stringResource(R.string.release_date).plus(": ")
                            .plus(detailsState.movie?.releaseDate),
                        fontSize = 14.sp
                    )
                    VerticalSpacer(10)
                    MultiLineText(
                        modifier = Modifier.padding(start = 6.dp),
                        fontSize = 14.sp,
                        color = Color.Black,
                        text = detailsState.movie?.genre ?: ""
                    )
                }
            }

            VerticalSpacer(size = 8)
            RegularText(
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.overview), fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )

            MultiLineText(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                fontSize = 14.sp,
                text = detailsState.movie?.overView ?: ""
            )
        }
    }
}

