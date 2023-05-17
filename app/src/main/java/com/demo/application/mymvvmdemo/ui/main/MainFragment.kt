package com.demo.application.mymvvmdemo.ui.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demo.application.mymvvmdemo.R
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileOutputStream


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var btn1 : SwitchCompat
    private lateinit var btn2 : SwitchCompat
    private lateinit var btnCopy : Button
    private lateinit var text : EditText
    private lateinit var layout : ConstraintLayout
    private var resolver : ContentResolver? = null
    private var clipboardManager : ClipboardManager? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        btn1 = view.findViewById(R.id.button)
        btn2 = view.findViewById(R.id.button2)
        btnCopy = view.findViewById(R.id.btnCopy)
        text = view.findViewById(R.id.message)
        layout = view.findViewById(R.id.main)

        resolver = context?.contentResolver
        clipboardManager = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        text.requestFocus()

        val font1 =  Typeface.createFromAsset(context?.assets,"fonts/aclonica.ttf")
        val font2 =  Typeface.createFromAsset(context?.assets,"fonts/vampiro_one.ttf")

        text.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                Log.d("Darren","focus")
            }else{
                Log.d("Darren","no focus")
            }
        }

        btn1.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                viewModel.tp.value = font1
            }else{
                viewModel.tp.value = font2
            }
        })

        btn2.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                viewModel.color.value = context?.getColor(R.color.purple_200)
            } else {
                viewModel.color.value = context?.getColor(R.color.teal_200)
            }
        })

        btnCopy.setOnClickListener{
            try{
                saveBitmapToDownload()
            }catch (e:Exception){
                Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.tp.observe(this, Observer {
            text.typeface = it
        })
        viewModel.color.observe(this,{
            text.setTextColor(it)
        })
    }

    fun getBitmap(view:View):Bitmap{
        val spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        val bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight,
            Bitmap.Config.ARGB_8888)
        val c = Canvas(bitmap)
        c.translate((-view.scrollX).toFloat(), (-view.scrollY).toFloat())
        view.draw(c)
        return bitmap
    }

    fun saveBitmapToDownload(){
        val bitmap = getBitmap(text)
        val downloadPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
        val fileName = "abc.png"
        val finalPath = "$downloadPath/$fileName"
        val myFile = File(finalPath)
        try {
            val fos = FileOutputStream(myFile)
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos)
            fos.close()
            val uri = FileProvider.getUriForFile(
                context!!,
                "com.demo.application.mymvvmdemo.fileProvider",
                myFile)
            val clip = ClipData.newUri(
                context?.contentResolver,
                "a Photo",
                uri
            )
            clipboardManager?.setPrimaryClip(clip)
            Snackbar.make(layout,"copy success",Snackbar.LENGTH_SHORT).show()
        }catch (e:java.lang.Exception){
            Toast.makeText(context!!,e.toString(),Toast.LENGTH_SHORT).show()
        }

    }
}