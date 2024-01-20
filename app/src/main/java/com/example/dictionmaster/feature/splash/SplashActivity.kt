package com.example.dictionmaster.feature.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dictionmaster.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen(
                startActivity = {
                    startActivity(
                        Intent(
                            this@SplashActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()
                }
            )
        }
    }
}