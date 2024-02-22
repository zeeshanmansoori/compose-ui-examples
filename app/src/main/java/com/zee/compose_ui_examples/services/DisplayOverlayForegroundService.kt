package com.zee.compose_ui_examples.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.zee.compose_ui_examples.R
import com.zee.compose_ui_examples.ui.MainActivity
import com.zee.compose_ui_examples.ui.overlay.OverlayViews
import com.zee.compose_ui_examples.ui.overlay.OverlayWindowListener

class DisplayOverlayForegroundService : Service() {
    override fun onBind(intent: Intent) = null

    private val overlayWindowListener: OverlayWindowListener = object : OverlayWindowListener {
        override fun onClose() {
            stopThisService()
        }

        override fun returnToHome() {
            onClose()
            val i = Intent()
            i.setClass(applicationContext, MainActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        }
    }

    companion object{
        const val TAG = "DisplayOverlayForegroundService"
    }
    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            displayNotification()
        }
        val overlayViews = OverlayViews(this)
        overlayViews.setWindowCloseListener(overlayWindowListener)
        Log.d(TAG, "onCreate: ")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayNotification() {
        val NOTIFICATION_CHANNEL_ID = "com.whiteboard"
        val channelName = "WhiteBoard service name"
        val chan = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_MIN
        )
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(chan)
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        val notification = notificationBuilder.setOngoing(true).setContentTitle("Service running")
            .setContentText("Drawing over other apps")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(2, notification)
    }

    private fun stopThisService() {
        stopSelf()
    }
}