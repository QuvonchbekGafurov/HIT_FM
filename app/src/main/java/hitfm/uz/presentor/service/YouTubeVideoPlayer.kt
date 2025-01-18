package hitfm.uz.presentor.service
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

object YouTubePlayerState {
    var youTubePlayer: YouTubePlayer? = null
    var isPlaying: MutableState<Boolean> = mutableStateOf(false)
}
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun YouTubeVideoPlayer(
    modifier: Modifier,
    videoId: String,
    lifecycleOwner: LifecycleOwner
) {
    val context= LocalContext.current
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
        // YouTube o'ynayotgan bo'lsa, radio avtomatik to'xtaydi
        LaunchedEffect(YouTubePlayerState.isPlaying.value) {
            if (YouTubePlayerState.isPlaying.value) {
                // Radio pauza qilinadi
                val pauseIntent = Intent(context, RadioService::class.java).apply {
                    action = RadioService.ACTION_PAUSE
                }
                context.startService(pauseIntent)
            }
        }

        // Pause YouTube if radio starts playing
        LaunchedEffect(RadioState.isPlaying.value) {
            if (RadioState.isPlaying.value) {
                YouTubePlayerState.youTubePlayer?.pause()
            }
        }
    }
}

