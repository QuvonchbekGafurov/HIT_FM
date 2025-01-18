package hitfm.uz.presentor.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hitfm.uz.presentor.presentation.ui.screen.MainScreen
import hitfm.uz.presentor.presentation.viewmodel.YouTubeViewModel
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