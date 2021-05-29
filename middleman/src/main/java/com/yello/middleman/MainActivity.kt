package com.yello.middleman

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userStringJson = intent.getStringExtra("userStringJson")
        val status = intent.getBooleanExtra("status", false)

        startService(
            Intent(this, UserService::class.java)
                .putExtra("userStringJson", userStringJson)
                .putExtra("status", status)
        )
        finish()
    }
}