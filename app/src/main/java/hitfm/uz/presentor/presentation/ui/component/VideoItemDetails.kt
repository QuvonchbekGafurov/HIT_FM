package com.mohamedbenrejeb.youtubecomposemotionlayout.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hitfm.uz.presentor.VideoUiState

@Composable
fun VideoItemDetails(
    videoUiState: VideoUiState,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier
        ) {
            Text(
                text = videoUiState.title,
                color = colorScheme.onBackground,
                style = typography.titleSmall
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "${videoUiState.author} ${videoUiState.viewsCount} ${videoUiState.publishDate}",
                color = colorScheme.onBackground.copy(alpha = 0.6f),
                style = typography.bodySmall
            )
        }
    }
}

