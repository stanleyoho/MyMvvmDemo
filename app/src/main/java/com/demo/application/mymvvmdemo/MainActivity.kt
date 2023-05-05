package com.demo.application.mymvvmdemo

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import com.demo.application.mymvvmdemo.ui.main.MainFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }

        Handler().postDelayed(Runnable {
            runOnUiThread{
                startActivity(Intent(this,MainActivity2::class.java))
                overridePendingTransition(R.anim.fw_slide_in_left,R.anim.fw_slide_out_right)
            }
        },3000)

    }
}