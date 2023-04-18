package com.demo.application.mymvvmdemo.ui.test02

class StubWeather : IWeather {

    var fakeIsSunny = false

    override fun isSunny(): Boolean {
        return fakeIsSunny
    }
}