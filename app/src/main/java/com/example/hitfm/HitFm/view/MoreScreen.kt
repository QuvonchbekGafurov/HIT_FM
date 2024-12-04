package com.example.hitfm.HitFm.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hitfm.R
import com.example.hitfm.HitFm.theme.Black

// 1. Roboto shrift oilasini yaratamiz

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MoreScreen(navController: NavController) {
    val robotoFontFamily = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_light, FontWeight.Light)
    )
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    val message = "Sizning yubormoqchi bo'lgan linkingiz yoki matningiz"
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween, // Elementlarni yuqori va pastki qismda ajratadi
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Еще",
            modifier = Modifier
                .padding(top = 20.dp),
            fontSize = 25.sp,
            color = Black,
            fontFamily = robotoFontFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            ImageInCircle(
                image = R.drawable.phone,
                imageSize = 20.dp,
                circleSize = 35.dp,
                color = Color.Green,
                modifier = Modifier .combinedClickable(
                    indication = null, // Soyani olib tashlaydi
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+998881149080"))
                        context.startActivity(intent)                    }
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Контакты",
                color = Black,
                fontFamily = robotoFontFamily,
                modifier = Modifier,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier,
             verticalAlignment = Alignment.CenterVertically) {
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = rememberModalBottomSheetState()
                ) {
                  BottomSheet()
                }
            }
            ImageInCircle(
                image = R.drawable.share,
                imageSize = 20.dp,
                circleSize = 35.dp,
                color = Color.Green,
                modifier = Modifier.combinedClickable(
                    indication = null, // Soyani olib tashlaydi
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        showBottomSheet=true
                    }
                )
            )
            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Поделиться",
                fontFamily = robotoFontFamily,
                modifier = Modifier,
                color = Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Мы в соцсетях",
            color = Black,
            modifier = Modifier
                .padding(top = 20.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            ImageInCircle(
                image = R.drawable.social,
                imageSize = 35.dp,
                circleSize = 35.dp,
                color = Color.Green,
                modifier = Modifier.combinedClickable(
                    indication = null, // Soyani olib tashlaydi
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hitfm.uz?igsh=MW5neXhxaDVsZXZzdw=="))
                        context.startActivity(intent)
                    }
                )
            )
            Spacer(modifier = Modifier.width(10.dp))

            ImageInCircle(
                image = R.drawable.telegram,
                imageSize = 35.dp,
                circleSize = 35.dp,
                color = Color.Green,
                modifier = Modifier.combinedClickable(
                    indication = null, // Soyani olib tashlaydi
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/yangiradio"))
                        context.startActivity(intent)
                    }
                )
            )

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            ImageInCircle(
                image = R.drawable.youtube,
                imageSize = 35.dp,
                circleSize = 35.dp,
                color = Color.Green,
                modifier = Modifier.combinedClickable(
                    indication = null, // Soyani olib tashlaydi
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        navController.navigate("screen2") // Ikkinchi ekranga o'tish
                    }
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Импульс ток шоу",
                fontFamily = robotoFontFamily,
                modifier = Modifier,
                color = Black,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }


        }
}


