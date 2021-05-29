package com.yello.middleman

import android.app.*
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.HandlerThread
import android.os.IBinder
import android.os.Process
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

/**
 * Created by µðšţãƒâ ™ on 29/05/2021.
 *  ->
 */
class UserService : Service() {
    private val mUserReceiver = UserBroadcastReceiver()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        val thread = HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND)
        thread.start()

        val intentFilter = IntentFilter("com.yello.middleman.User")
        registerReceiver(mUserReceiver, intentFilter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val userStringJson = intent?.getStringExtra("userStringJson")
        val status = intent?.getBooleanExtra("status", false)

        if (status != null && status) {
            goToEmitterActivity(userStringJson)
        } else {
            userStringJson?.let {
                val receiverAppIntent =
                    packageManager.getLaunchIntentForPackage("com.yello.receiver")
                if (receiverAppIntent != null) {
                    receiverAppIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    receiverAppIntent.putExtra("user", it)
                    startActivity(receiverAppIntent)
                } else {
                    Toast.makeText(
                        this,
                        "we need install Receiver app first",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startMyOwnForeground()
        else notification(pendingIntent)

        return START_STICKY
    }

    private fun goToEmitterActivity(userStringJson: String?) {
        val intent = Intent()
        intent.putExtra("user", userStringJson)
        intent.action = "com.yello.emitter.User"
        sendBroadcast(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMyOwnForeground() {
        val NOTIFICATION_CHANNEL_ID = applicationContext.packageName
        val chan = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "CHANNEL_USER",
            NotificationManager.IMPORTANCE_NONE
        )
        chan.lightColor = getColor(R.color.background_color)
        chan.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        val manager = (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(chan)
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(getText(R.string.app_name))
            .setContentText("MiddleMan is open")
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(0, notification)
    }

    private fun notification(pendingIntent : PendingIntent) {
        val notification = Notification.Builder(this)
            .setContentTitle(getText(R.string.app_name))
            .setContentTitle(getText(R.string.app_name))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pendingIntent)
            .setTicker("MiddleMan is open")
            .build()
        startForeground(0, notification)
    }


    override fun onDestroy() {
        unregisterReceiver(mUserReceiver)
        stopSelf()
        super.onDestroy()
    }
}