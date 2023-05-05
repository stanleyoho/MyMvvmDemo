package com.demo.application.mymvvmdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable {
            runOnUiThread{
                startActivity(Intent(this,MainActivity::class.java))
                overridePendingTransition(R.anim.fw_slide_in_left,R.anim.fw_slide_out_right)
            }
        },2000)
    }
}