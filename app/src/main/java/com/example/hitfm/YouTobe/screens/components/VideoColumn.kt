package com.mohamedbenrejeb.youtubecomposemotionlayout.screens.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hitfm.R
import com.example.hitfm.Test.apimodel.Item
import com.example.hitfm.Test.apimodel.YouTubeResponse
import com.mohamedbenrejeb.youtubecomposemotionlayout.model.VideoUiState
import com.mohamedbenrejeb.youtubecomposemotionlayout.ui.theme.YoutubeComposeMotionLayoutTheme
@Composable
fun VideoColumn(
    onVideoItemClick: (videoUiState: VideoUiState) -> Unit,
    videoUiStateList: List<VideoUiState>,
    youtobeResponse: YouTubeResponse,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) {
    val videoUiStateList1 = mapItemsToVideoUiState(youtobeResponse.items)

    LazyColumn(
        state = listState,
        modifier = modifier
    ) {
        items(videoUiStateList1) { videoUiState ->
            VideoItem(
                onVideoItemClick = {
                    onVideoItemClick(it) // Item bosilganda `onVideoItemClick` chaqiriladi
                    Log.e("TAG", "VideoColumnonClick:$it ", )
                },
                videoUiState = videoUiState,
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .fillMaxWidth()
            )
        }
    }
}

// Funksiya: Item ro'yxatini VideoUiState ro'yxatiga o'zgartirish
fun mapItemsToVideoUiState(items: List<Item>): List<VideoUiState> {
    return items.map { item ->
        VideoUiState(
            id = item.id,
            title = item.snippet.title,
            author = item.snippet.channelTitle,
            authorImageResId = R.drawable.svg_hit_fm, // Kanal rasmi uchun tasvir resursini belgilab qo'yish kerak
            viewsCount = "1K views", // Asl API’dan kelayotgan ma'lumot bo‘lsa, uni mos ravishda joylashtiring
            publishDate = item.snippet.publishedAt,
            thumbnailResId = item.snippet.thumbnails.medium.url,// Tasvir URL'dan yuklash uchun Glide yoki Coil kutubxonasidan foydalaning
            youtubevideoid = item.snippet.resourceId.videoId
        )
    }
}
