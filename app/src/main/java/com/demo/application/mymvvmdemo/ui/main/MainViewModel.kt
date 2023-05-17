package com.demo.application.mymvvmdemo.ui.main

import android.graphics.Typeface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var tp = MutableLiveData<Typeface>()
    private val _tp: MutableLiveData<Typeface>
        get() = tp

    var color = MutableLiveData<Int>()
    private val _color: MutableLiveData<Int>
        get() = color
}