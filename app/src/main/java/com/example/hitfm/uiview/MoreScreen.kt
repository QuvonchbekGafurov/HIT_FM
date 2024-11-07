package com.example.hitfm.uiview

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
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
import com.example.hitfm.R

// 1. Roboto shrift oilasini yaratamiz

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MoreScreen() {
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
            .padding(20.dp),
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
                text = "контакт",
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
                text = "поделиться приложением",
                fontFamily = robotoFontFamily,
                modifier = Modifier,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Мы в соцсетях",
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
                link = "https://www.instagram.com/hitfm.uz?igsh=MW5neXhxaDVsZXZzdw==",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.width(10.dp))

            ImageInCircle(
                image = R.drawable.telegram,
                imageSize = 35.dp,
                circleSize = 35.dp,
                color = Color.Green,
                link = "https://t.me/yangiradio",
                modifier = Modifier
            )

        }

    }
}


