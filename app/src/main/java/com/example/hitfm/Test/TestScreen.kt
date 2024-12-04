package com.example.hitfm.Test
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.example.hitfm.Test.apimodel.YouTubeResponse
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.delay

object YouTubePlayerState {
    var youTubePlayer: YouTubePlayer? = null
    var isPlaying: MutableState<Boolean> = mutableStateOf(false)
}
@Composable
fun YouTubeVideoPlayer(
    modifier: Modifier,
    videoId: String,
    lifecycleOwner: LifecycleOwner
) {
    Log.e("TAG", "YouTubeVideoPlayerid: $videoId", )
    if (videoId!=null) {
        AndroidView(
            modifier = modifier,
            factory = {
                YouTubePlayerView(context = it).apply {
                    lifecycleOwner.lifecycle.addObserver(this)
                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(player: YouTubePlayer) {
                            YouTubePlayerState.youTubePlayer = player
                            player.loadVideo(videoId, 0f)
                            player.addListener(object : AbstractYouTubePlayerListener() {
                                override fun onStateChange(
                                    youTubePlayer: YouTubePlayer,
                                    state: PlayerConstants.PlayerState
                                ) {
                                    when (state) {
                                        PlayerConstants.PlayerState.PLAYING -> {
                                            YouTubePlayerState.isPlaying.value = true
                                            Log.d("YouTubePlayerState", "Video Playing")
                                        }
                                        PlayerConstants.PlayerState.PAUSED -> {
                                            YouTubePlayerState.isPlaying.value = false
                                            Log.d("YouTubePlayerState", "Video Paused")
                                        }
                                        PlayerConstants.PlayerState.ENDED -> {
                                            YouTubePlayerState.isPlaying.value = false
                                            Log.d("YouTubePlayerState", "Video Ended")
                                        }
                                        PlayerConstants.PlayerState.BUFFERING -> {
                                            Log.d("YouTubePlayerState", "Video Buffering")
                                        }
                                        else -> {
                                            Log.d("YouTubePlayerState", "Video State: $state")
                                        }
                                    }
                                    Log.e("TAG", "onStateChange: ${YouTubePlayerState.isPlaying.value}", )
                                }
                            })
                        }
                    })
                }
            }
        )

        LaunchedEffect(videoId) {
            YouTubePlayerState.youTubePlayer?.loadVideo(videoId, 0f)
        }
    }
}

