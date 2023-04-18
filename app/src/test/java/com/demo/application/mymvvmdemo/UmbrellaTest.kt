package com.demo.application.mymvvmdemo

import com.demo.application.mymvvmdemo.ui.test02.StubWeather
import com.demo.application.mymvvmdemo.ui.test02.Umbrella
import org.junit.Assert
import org.junit.Test

class UmbrellaTest {

    @Test
    fun totalPrice_sunnyDay(){
        val umbrella = Umbrella()
        val weather = StubWeather()
        weather.fakeIsSunny = true

        val actual = umbrella.totalPrice(weather,3,100)
        val expect = 270
        Assert.assertEquals(expect,actual)
    }

    @Test
    fun totalPrice_rainingDay(){
        val umbrella = Umbrella()
        val weather = StubWeather()
        weather.fakeIsSunny = false

        val actual = umbrella.totalPrice(weather,3,100)
        val expect = 270
        Assert.assertEquals(expect,actual)
    }
}