package com.example.hitfm.HitFm.view

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hitfm.R
import com.example.hitfm.HitFm.RadioService
import com.example.hitfm.HitFm.RadioState
import com.example.hitfm.HitFm.theme.Black

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RadioPlayer(){
    val context = LocalContext.current

    // LaunchedEffect bilan dastlabki holatni o'rnatamiz
//    LaunchedEffect(Unit) {
//        val playIntent = Intent(context, RadioService::class.java).apply {
//            action = RadioService.ACTION_PLAY
//        }
//        context.startService(playIntent)
//    }


    val imageResource = if (RadioState.isPlaying.value) {
        painterResource(id = R.drawable.pause)  // Pause icon
    } else {
        painterResource(id = R.drawable.play)   // Play icon
    }

    // UI qismi
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "ХИТ FM",
            color = Black,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 35.dp)
                .align(Alignment.TopCenter),
            fontSize = 25.sp
        )
        Column(modifier = Modifier
            .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Sample Image",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 65.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit // Rasmlarni moslashtirish
            )

            Image(
                painter = imageResource,
                contentDescription = if (RadioState.isPlaying.value) "Pause" else "Play",
                modifier = Modifier
                    .padding(top = 60.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp)
                    .clickable(
                        indication = null,  // Soya va vizual feedbackni olib tashlash
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        if (RadioState.isPlaying.value) {
                            // Stop playing
                            val pauseIntent = Intent(context, RadioService::class.java).apply {
                                action = RadioService.ACTION_PAUSE
                            }
                            context.startService(pauseIntent)
                            RadioState.isPlaying.value = false

                        } else {
                            // Start playing
                            val playIntent = Intent(context, RadioService::class.java).apply {
                                action = RadioService.ACTION_PLAY
                            }
                            context.startService(playIntent)
                            RadioState.isPlaying.value = true

                        }

                    },
                contentScale = ContentScale.Fit
            )
        }
    }
}


