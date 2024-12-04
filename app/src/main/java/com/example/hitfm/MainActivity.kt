package com.example.hitfm.HitFm

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.hitfm.HitFm.view.MainScreen
import com.mohamedbenrejeb.youtubecomposemotionlayout.screens.home.HomeScreen

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
              MainScreen()
        }
    }

}
