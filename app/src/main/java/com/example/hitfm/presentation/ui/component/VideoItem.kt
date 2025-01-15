package com.mohamedbenrejeb.youtubecomposemotionlayout.screens.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hitfm.model.VideoUiState

@Composable
fun VideoItem(
    onVideoItemClick: (videoUiState: VideoUiState) -> Unit,
    videoUiState: VideoUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onVideoItemClick(videoUiState) }
    ) {
        VideoItemThumbnail(
            videoUiState = videoUiState,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .weight(1f)
        )
        VideoItemDetails(
            videoUiState = videoUiState,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .weight(1f)
        )
    }

}

