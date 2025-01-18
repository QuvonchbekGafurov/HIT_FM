package hitfm.uz.presentor

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import hitfm.uz.presentor.navigation.MyApp
import hitfm.uz.presentor.presentation.viewmodel.YouTubeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val youTubeViewModel: YouTubeViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
              MyApp(viewModel = youTubeViewModel)
        }
    }

}
