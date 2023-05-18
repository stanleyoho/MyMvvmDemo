package com.demo.application.mymvvmdemo

import android.Manifest
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class SplashActivity : AppCompatActivity() {
    val STORAGE_PERMISSION_CODE = 2296

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        checkStoragePermission()
    }

    private fun requestPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data = Uri.parse(String.format("package:%s", applicationContext.packageName))
                startActivityForResult(intent, STORAGE_PERMISSION_CODE)
            } catch (e: Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                startActivityForResult(intent, STORAGE_PERMISSION_CODE)
            }
        } else {
            //below android 11
            ActivityCompat.requestPermissions(
                this@SplashActivity,
                arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE),
                2296
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 2296){
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // perform action when allow permission success
                    goToMainPage()
                } else {
                    Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT).show()
                }
            }else{
                if(grantResults.contains(-1)){
                    Toast.makeText(this, "Allow permission for storage access!", Toast.LENGTH_SHORT).show()
                }else{
                    goToMainPage()
                }
            }
        }
    }

    fun goToMainPage(){
        startActivity(Intent(this,MainActivity::class.java))
        overridePendingTransition(R.anim.fw_slide_in_left,R.anim.fw_slide_out_right)
        finish()
    }

    private fun checkStoragePermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            //android 11 up
            if (Environment.isExternalStorageManager()) {
                // perform action when allow permission success
                goToMainPage()
            } else {
                requestPermission()
            }
        }else{
            //lower than android 11
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // 儲存裝置權限已授予
                // 在此處執行您的程式碼
                goToMainPage()
            } else {
                // 儲存裝置權限未授予，請向使用者請求權限
                requestPermission()
            }
        }

    }
}