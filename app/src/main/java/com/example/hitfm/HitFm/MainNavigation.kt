package com.example.hitfm.HitFm

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hitfm.HitFm.view.MainScreen
import com.example.hitfm.Test.YouTubeViewModel
import com.mohamedbenrejeb.youtubecomposemotionlayout.screens.home.HomeScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun MyApp(viewModel: YouTubeViewModel) {
    val navController = rememberNavController() // NavController'ni yaratish

    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") { MainScreen(viewModel,navController) }
        composable("screen2") { HomeScreen(viewModel,navController) }
    }
}