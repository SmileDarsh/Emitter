package com.yello.receiver

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.yello.receiver.model.User
import com.yello.receiver.room.RoomDB
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_user.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mRoom: RoomDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRoom = RoomDB.getInstance(this)!!

        val userStringJson = intent?.getStringExtra("user")

        userStringJson?.let { initUserData(it) }
    }

    private fun initUserData(userStringJson: String) {
        val user = Gson().fromJson(userStringJson, User::class.java)
        vUser.visibility = View.VISIBLE
        btnSave.visibility = View.VISIBLE
        val address = "${user.address?.suite}, ${user.address?.street}, ${user.address?.city}"
        tvName.text = user.name
        tvUsername.text = user.username
        tvEmail.text = user.email
        tvPhone.text = user.phone
        tvAddress.text = address
        tvCompany.text = user.company?.name

        ivMap.setOnClickListener {
            val uri =
                Uri.parse("https://www.google.com/maps/search/?api=1&query=${user.address?.geo?.lat},${user.address?.geo?.lng}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }

        btnSave.setOnClickListener {
            val middlemanAppIntent = packageManager.getLaunchIntentForPackage("com.yello.middleman")
            if (middlemanAppIntent != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    mRoom.userDao().insertUser(user)
                    middlemanAppIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    middlemanAppIntent.putExtra("userStringJson", userStringJson)
                    middlemanAppIntent.putExtra("status", true)
                    startActivity(middlemanAppIntent)
                    finish()
                }
            }
        }
    }
}