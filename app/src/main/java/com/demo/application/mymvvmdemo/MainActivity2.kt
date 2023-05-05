package com.demo.application.mymvvmdemo

import android.content.*
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import java.io.File
import java.io.FileOutputStream


class MainActivity2 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val image : ImageView = findViewById(R.id.iv)
        image.setOnClickListener{
            val bitmap = image.drawable.toBitmap(100,100, Bitmap.Config.RGB_565)
            val FILENAME = "image.png"
            val PATH = "/mnt/sdcard/$FILENAME"
            val f = File(PATH)
            try{
                val stream = FileOutputStream(f)
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
                stream.flush()
                stream.close()
                val yourUri = Uri.fromFile(f)
                copyImageToClipboard(yourUri)
            }catch (e:Exception){
                Log.d("error",e.printStackTrace().toString())
            }

        }
    }

    private fun copyImageToClipboard(uri: Uri) {
            val mClipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val values = ContentValues(2)
            values.put(MediaStore.Images.Media.MIME_TYPE, "Image/jpg")
            values.put(MediaStore.Images.Media.DATA, "file:/" + uri.getPath())
            val theContent = contentResolver
            val imageUri: Uri? = theContent.insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, values)
            val theClip = ClipData.newUri(contentResolver, "Image", imageUri)
            mClipboard.setPrimaryClip(theClip)
    }
}