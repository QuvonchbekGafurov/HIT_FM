package com.example.hitfm.uiview
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hitfm.NavItem
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import com.example.hitfm.R
import com.example.hitfm.RadioService


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val context= LocalContext.current

    val navItemList = listOf(
        NavItem("слушать", Icons.Filled.MusicNote,0),
        NavItem("еще", Icons.Filled.MoreHoriz,0),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Column {
                if(selectedIndex==1)
                {
                    val imageResource = if (RadioService.RadioState.isPlaying.value) {
                        R.drawable.pause  // Pause icon
                    } else {
                        R.drawable.play   // Play icon
                    }
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(100.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White),
                     horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.hit_fm),
                        contentDescription = "",
                        modifier = Modifier.fillMaxHeight()
                    )
                    Row(modifier = Modifier.align(Alignment.CenterVertically), horizontalArrangement = Arrangement.End,) {
                        ImageInCircle(color = Color.White, image =R.drawable.previus_arrows, imageSize = 25.dp, modifier = Modifier)
                        Image(painter = painterResource(id = imageResource), contentDescription = "",
                            modifier = Modifier
                                .size(25.dp)
                                .fillMaxHeight()
                                .align(alignment = Alignment.CenterVertically)
                                .clickable(
                                    indication = null,  // Soya va vizual feedbackni olib tashlash
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    if (RadioService.RadioState.isPlaying.value) {
                                        // Stop playing
                                        val pauseIntent =
                                            Intent(context, RadioService::class.java).apply {
                                                action = RadioService.ACTION_PAUSE
                                            }
                                        context.startService(pauseIntent)
                                        RadioService.RadioState.isPlaying.value = false
                                        Log.e(
                                            "TAG",
                                            "RadioPlayer: ${RadioService.RadioState.isPlaying.value}",
                                        )

                                    } else {
                                        // Start playing
                                        val playIntent =
                                            Intent(context, RadioService::class.java).apply {
                                                action = RadioService.ACTION_PLAY
                                            }
                                        context.startService(playIntent)
                                        RadioService.RadioState.isPlaying.value = true
                                        Log.e(
                                            "TAG",
                                            "RadioPlayer: ${RadioService.RadioState.isPlaying.value}",
                                        )

                                    }

                                },
                        )
                        ImageInCircle(color = Color.White, image =R.drawable.next_arrows,imageSize = 25.dp, modifier = Modifier)

                    }
                }}
                NavigationBar (
                    containerColor = Color.White// Set the background color of the NavigationBar

                ){
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            },
                            icon = {
                                BadgedBox(badge = {
                                    if (navItem.badgeCount > 0)
                                        Badge() {
                                            Text(text = navItem.badgeCount.toString())
                                        }
                                }) {
                                    Icon(imageVector = navItem.icon, contentDescription = "Icon")
                                }

                            },
                            label = {
                                Text(text = navItem.label)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.Black,  // Specify color for the icon when selected
                                unselectedIconColor = Color.LightGray, // Specify color for the icon when unselected
                                selectedTextColor = Color.Black,   // Specify color for text when selected
                                unselectedTextColor = Color.LightGray, // Specify color for text when unselected
                                indicatorColor = Color.White  // Set indicator color to transparent
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding),selectedIndex)
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int) {
    when(selectedIndex){
        0-> RadioPlayer()
        1-> MoreScreen()
    }
}

@Composable
fun ImageInCircle(
    color: Color,
    image: Int,  // Rasmni parametr sifatida qabul qilamiz
    imageSize: Dp = 32.dp,  // Rasmning o'lchamini ham parametr qilamiz
    circleSize: Dp = 64.dp,  // Aylananing o'lchamini parametr qilamiz
    link:String?=null,
    modifier: Modifier
) {
    val context= LocalContext.current
    Box(
        modifier = modifier
            .size(circleSize)  // Aylananing o'lchami
            .background(
                color = color,
                shape = CircleShape
            ).apply {
                if (link != null) {
                    clickable() {
                            link?.let {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                                context.startActivity(intent)
                            }
                    }
                }
            }
        ,
        contentAlignment = Alignment.Center  // Rasmni o'rtada joylashtirish
    ) {
        Image(
            painterResource(id = image),  // Funksiya orqali berilgan rasmni ko'rsatamiz
            contentDescription = "Image in Circle",
            modifier = Modifier.size(imageSize)  // Rasmingizning o'lchami
        )
    }
}
