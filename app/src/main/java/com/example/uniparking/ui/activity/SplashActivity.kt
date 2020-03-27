package com.example.uniparking.ui.activity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.uniparking.R
import com.example.uniparking.utils.goToActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            goToActivity(ParkedVehiclesActivity::class.java)
            finish()
        }, 1000)

    }

}