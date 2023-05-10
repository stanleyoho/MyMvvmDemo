package com.demo.application.mymvvmdemo.test.test02

class StubWeather : IWeather {

    var fakeIsSunny = false

    override fun isSunny(): Boolean {
        return fakeIsSunny
    }
}