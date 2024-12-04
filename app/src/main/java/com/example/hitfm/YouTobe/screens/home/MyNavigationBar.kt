package com.mohamedbenrejeb.youtubecomposemotionlayout.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.YoutubeSearchedFor
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.hitfm.HitFm.NavItem
import com.example.hitfm.HitFm.theme.Black
import com.example.hitfm.HitFm.view.SelectedIndex.selectedIndex
import com.mohamedbenrejeb.youtubecomposemotionlayout.ui.theme.YoutubeComposeMotionLayoutTheme

val navItemList = listOf(
    NavItem("Слушать", Icons.Filled.MusicNote,0),
    NavItem("Еще", Icons.Filled.MoreHoriz,0),
)

@Composable
fun MyNavigationBar(
    modifier: Modifier ,
    items: List<NavItem> = navItemList,
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Column(
        modifier = modifier
    ) {
        Divider(
            thickness = 1.dp,
            color = colorScheme.onBackground.copy(alpha = 0.2f)
        )

        NavigationBar (
            modifier =modifier,
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
                        selectedTextColor = Black,   // Specify color for text when selected
                        unselectedTextColor = Color.LightGray, // Specify color for text when unselected
                        indicatorColor = Color.White  // Set indicator color to transparent
                    )
                )
            }
        }
    }
}

