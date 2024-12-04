package com.example.hitfm

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class BaseApplication :Application(){
    companion object{
        const val CHANNEL_1_ID="chanel1"
    }
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }
    private fun createNotificationChannels(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel1=NotificationChannel(
                CHANNEL_1_ID,
                "Channel Satu",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel1.description="Ini Adalah channel 1"

            val manager=getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel1)
        }
    }

}