package com.example.hitfm.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hitfm.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.hitfm.ui.theme.Black

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheet() {
    val context= LocalContext.current
    fun copyToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Link", text)
        clipboard.setPrimaryClip(clip)
        // Copy muvaffaqiyatli amalga oshganini ko'rsatish uchun Toast
        Toast.makeText(context, "Link nusxalandi", Toast.LENGTH_SHORT).show()
    }
    fun shareViaSMS(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("sms:")
        intent.putExtra("sms_body", link)
        context.startActivity(intent)
    }
    fun shareViaGmail(link: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Link")
        intent.putExtra(Intent.EXTRA_TEXT, link)
        intent.setPackage("com.google.android.gm")
        context.startActivity(Intent.createChooser(intent, "Send via Gmail"))
    }
    fun shareViaTelegram(link: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, link)
        intent.setPackage("org.telegram.messenger")
        context.startActivity(Intent.createChooser(intent, "Share via Telegram"))
    }
    fun copyTomClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Link", text)
        clipboard.setPrimaryClip(clip)

        // Foydalanuvchiga muvaffaqiyatli nusxalanganini bildirish uchun
        Toast.makeText(context, "Link nusxalandi", Toast.LENGTH_SHORT).show()
    }
    Column(
        modifier =Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Поделиться",
            modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold, fontSize = 25.sp, color = Black
        )
        Spacer(modifier = Modifier.height(30.dp))

        Row {
            Spacer(modifier =Modifier.width(20.dp))
            Row {
                Box(
                    modifier = Modifier
                        .size(50.dp)  // Aylananing o'lchami
                        .background(
                            color = Color.Black,
                            shape = CircleShape
                        )
                        .combinedClickable(
                            indication = null, // Soyani olib tashlaydi
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                shareViaTelegram("https://hitfm.uz/")
                            }
                        ),
                    contentAlignment = Alignment.Center  // Rasmni o'rtada joylashtirish
                ) {
                    Image(
                        painterResource(id = R.drawable.telegram),  // Funksiya orqali berilgan rasmni ko'rsatamiz
                        contentDescription = "Image in Circle",
                        modifier = Modifier.size(50.dp)  // Rasmingizning o'lchami
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))

            Row {
                Box(
                    modifier = Modifier
                        .size(50.dp)  // Aylananing o'lchami
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .combinedClickable(
                            indication = null, // Soyani olib tashlaydi
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                shareViaGmail("https://hitfm.uz/")
                            }
                        ),
                    contentAlignment = Alignment.Center  // Rasmni o'rtada joylashtirish
                ) {
                    Image(
                        painterResource(id = R.drawable.gmail),  // Funksiya orqali berilgan rasmni ko'rsatamiz
                        contentDescription = "Image in Circle",
                        modifier =Modifier.size(30.dp)  // Rasmingizning o'lchami
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))

            Row {
                Box(
                    modifier = Modifier
                        .size(50.dp)  // Aylananing o'lchami
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .combinedClickable(
                            indication = null, // Soyani olib tashlaydi
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                shareViaSMS("https://hitfm.uz/")
                            }
                        ),
                    contentAlignment = Alignment.Center  // Rasmni o'rtada joylashtirish
                ) {
                    Image(
                        painterResource(id = R.drawable.sms),  // Funksiya orqali berilgan rasmni ko'rsatamiz
                        contentDescription = "Image in Circle",
                        modifier = androidx.compose.ui.Modifier.size(40.dp)  // Rasmingizning o'lchami
                    )
                }
            }
        }
        Spacer(modifier =Modifier.height(30.dp))
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier =Modifier.width(20.dp))
            Image(painter = painterResource(id = R.drawable.baseline_content_copy_24),
                contentDescription ="Copy",
                modifier = Modifier
                    .size(40.dp)
                    .combinedClickable(
                        indication = null, // Soyani olib tashlaydi
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            copyTomClipboard(context,"https://hitfm.uz/")
                        }
                    ),

            )
            Spacer(modifier =Modifier.width(30.dp))
            Text(text = "Поделиться ссылкой")
        }
        Spacer(modifier = Modifier.height(10.dp))
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun show() {
    BottomSheet()
}