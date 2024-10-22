package com.example.hitfm.uiview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hitfm.R
import com.example.hitfm.ui.theme.PurpleGrey80

@Composable
fun MoreScreen() {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween, // Elementlarni yuqori va pastki qismda ajratadi
    ) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "More",
            modifier = Modifier
                .padding(top = 20.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            ImageInCircle(image =R.drawable.phone, imageSize = 25.dp, circleSize = 35.dp, color = Color.Green )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "contact",
                modifier = Modifier,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            ImageInCircle(image =R.drawable.share, imageSize = 25.dp, circleSize = 35.dp, color = Color.Green )
            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "share the app",
                modifier = Modifier,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "We are on social media",
            modifier = Modifier
                .padding(top = 20.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically) {

            ImageInCircle(image =R.drawable.vk, imageSize = 35.dp, circleSize = 35.dp, color = Color.Green )
            Spacer(modifier = Modifier.width(10.dp))

            ImageInCircle(image =R.drawable.odnoklassniki, imageSize = 35.dp, circleSize = 35.dp, color= Color.Green )

        }

    }
}


