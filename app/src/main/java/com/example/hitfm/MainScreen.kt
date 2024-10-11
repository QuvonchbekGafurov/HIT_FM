package com.example.hitfm

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.icu.text.RelativeDateTimeFormatter.Style
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RadioPlayer(){
    val context = LocalContext.current

    // LaunchedEffect bilan dastlabki holatni o'rnatamiz
    LaunchedEffect(Unit) {
        val playIntent = Intent(context, RadioService::class.java).apply {
            action = RadioService.ACTION_PLAY
        }
        context.startService(playIntent)
    }


    val imageResource = if (RadioService.RadioState.isPlaying.value) {
        painterResource(id = R.drawable.pause)  // Pause icon
    } else {
        painterResource(id = R.drawable.play)   // Play icon
    }

    // UI qismi
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        Text(
            text = "HIT FM",
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.TopCenter),
            fontSize = 25.sp
        )
        Column(modifier = Modifier
            .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.hit_fm),
                contentDescription = "Sample Image",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 65.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit // Rasmlarni moslashtirish
            )

            Image(
                painter = imageResource,
                contentDescription = if (RadioService.RadioState.isPlaying.value) "Pause" else "Play",
                modifier = Modifier
                    .padding(top = 60.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(80.dp)
                    .clickable(
                        indication = null,  // Soya va vizual feedbackni olib tashlash
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (RadioService.RadioState.isPlaying.value) {
                            // Stop playing
                            val pauseIntent = Intent(context, RadioService::class.java).apply {
                                action = RadioService.ACTION_PAUSE
                            }
                            context.startService(pauseIntent)
                            RadioService.RadioState.isPlaying.value=false
                            Log.e("TAG", "RadioPlayer: ${RadioService.RadioState.isPlaying.value}", )

                        } else {
                            // Start playing
                            val playIntent = Intent(context, RadioService::class.java).apply {
                                action = RadioService.ACTION_PLAY
                            }
                            context.startService(playIntent)
                            RadioService.RadioState.isPlaying.value=true
                            Log.e("TAG", "RadioPlayer: ${RadioService.RadioState.isPlaying.value}", )

                        }

                    },
                contentScale = ContentScale.Fit
            )
        }
    }
    Log.e("TAG", "RadioPlayer: ${RadioService.RadioState.isPlaying.value}", )

}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShowUi() {
    RadioPlayer()
}

