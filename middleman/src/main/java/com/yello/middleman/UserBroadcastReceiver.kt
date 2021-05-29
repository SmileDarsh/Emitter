package com.yello.middleman

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
class UserBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val userStringJson = intent?.getStringExtra("user")
        context?.startActivity(
            Intent(context, MainActivity::class.java)
                .putExtra("userStringJson", userStringJson)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }
}