package com.yello.emitter.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import com.yello.emitter.user.domain.model.User
import com.yello.emitter.user.presentation.activities.UsersActivity

/**
 * Created by µðšţãƒâ ™ on 27/05/2021.
 *  ->
 */
class UserBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val user = Gson().fromJson(intent?.getStringExtra("user"), User::class.java)
        context?.startActivity(
            Intent(context, UsersActivity::class.java)
                .putExtra("user", user)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        )
    }
}