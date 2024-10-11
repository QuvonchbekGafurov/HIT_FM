package com.example.hitfm

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import android.app.Service
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import java.util.concurrent.TimeUnit


@RequiresApi(Build.VERSION_CODES.Q)
class RadioService : Service() {
    private lateinit var player: ExoPlayer
    private lateinit var notificationManager: NotificationManagerCompat

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var stopRunnable: Runnable
    override fun onCreate() {
        super.onCreate()
        Log.e("TAG", "onCreate: Start Service", )
        player = ExoPlayer.Builder(this).build()


        // Xizmatni foregroundda ishga tushirish
        startForegroundService()

        // Media manbani tayyorlash (stream URL)
        val streamUrl = "http://91.203.172.88:8000/yoshlarovoziaac"
        val mediaItem = MediaItem.fromUri(streamUrl)
        player.setMediaItem(mediaItem)
        player.prepare()

        stopRunnable = Runnable {
            stopSelf() // Service ni to'xtatish
            Log.e("TAG", "Service has been stopped after 3 hours")
        }
        handler.postDelayed(stopRunnable, TimeUnit.HOURS.toMillis(2))
    }

    private fun startForegroundService() {
        // Xizmatni foregroundda ishga tushiruvchi intent
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val picture = BitmapFactory.decodeResource(resources, R.drawable.hit_fm)


        // Xabarnoma manageri
        notificationManager = NotificationManagerCompat.from(this)
        val builder = NotificationCompat.Builder(this, BaseApplication.CHANNEL_1_ID)
            .setSmallIcon(R.drawable.hit_fm)
            .setContentTitle("HIT FM")
            .setContentText("90.8")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setContentIntent(pendingIntent)
            .setLargeIcon(picture)
            .addAction(R.drawable.baseline_skip_previous_24,"Prew",null)
            .addAction(if (RadioState.isPlaying.value) R.drawable.baseline_pause_24 else R.drawable.baseline_play_circle_24,
            if (RadioState.isPlaying.value) "Pause" else "Play",
            getActionIntent(if (RadioState.isPlaying.value) ACTION_PAUSE else ACTION_PLAY))
            .addAction(R.drawable.baseline_skip_next_24,"next",null)
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(0,1,2)
            )
            .setColor(Color.WHITE)
            .setColorized(true)
            .setSilent(true)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setOnlyAlertOnce(true)
            .setOngoing(true)



        val notification = builder.build()

        // Servisni foregroundda ishga tushirish
        startForeground(1, notification)
    }

    private fun getActionIntent(action: String): PendingIntent {
        val intent = Intent(this, RadioService::class.java).apply {
            this.action = action
        }
        return PendingIntent.getService(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Foydalanuvchi xabarnomadagi tugmalarni bosganda nima qilishni belgilash
        when (intent?.action) {
            ACTION_PLAY -> {
                if (!player.isPlaying) {
                    player.prepare()  // Tayyorlaymiz
                    player.play()
                    RadioState.isPlaying.value = true // Singleton holatini yangilash
                }
            }
            ACTION_PAUSE -> {
                if (player.isPlaying) {
                    player.stop()
                    RadioState.isPlaying.value = false // Singleton holatini yangilash
                }
            }
        }
        updateNotification()
        return START_STICKY
    }
    override fun onDestroy() {
        super.onDestroy()
        player.release() // Player resurslarini bo'shatish
        RadioState.isPlaying.value=false
        Log.e("TAG", "onDestroy:  Service Destroy", )
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        const val ACTION_PLAY = "com.example.hitfm.ACTION_PLAY"
        const val ACTION_PAUSE = "com.example.hitfm.ACTION_PAUSE"
    }
    object RadioState {
        var isPlaying = mutableStateOf(false)
    }
    // Method to update notification
    private fun updateNotification() {
        // Call the startForegroundService method to update the notification
        startForegroundService()
    }

}



