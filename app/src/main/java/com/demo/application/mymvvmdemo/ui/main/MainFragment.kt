package com.demo.application.mymvvmdemo.ui.main

import android.graphics.Typeface
import android.os.*
import androidx.lifecycle.ViewModelProvider
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.FOCUSABLE
import android.view.View.FOCUSABLE_AUTO
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.demo.application.mymvvmdemo.R
import java.lang.Exception

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var btn1 : Button
    private lateinit var btn2 : Button
    private lateinit var text : EditText

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        btn1 = view.findViewById(R.id.button)
        btn2 = view.findViewById(R.id.button2)
        text = view.findViewById(R.id.message)
        text.requestFocus()


        text.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                Log.d("Darren","focus")
            }else{
                Log.d("Darren","no focus")
            }
        }

        btn1.setOnClickListener{
            try{
                val tp1 = Typeface.createFromAsset(context?.assets,"fonts/aclonica.ttf")
                text.typeface = tp1

            }catch (e:Exception){
                Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show()
            }

        }

        btn2.setOnClickListener{
            try{
                val tp1 = Typeface.createFromAsset(context?.assets,"fonts/vampiro_one.ttf")
                text.typeface = tp1
            }catch (e:Exception){
                Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}