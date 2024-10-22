package com.example.hitfm

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.hitfm.ui.theme.HITFMTheme
import com.example.hitfm.uiview.MainScreen
import com.example.hitfm.uiview.RadioPlayer
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HITFMTheme {
                MainScreen()
            }
        }
    }

}
