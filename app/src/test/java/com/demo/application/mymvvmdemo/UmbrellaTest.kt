package com.demo.application.mymvvmdemo

import com.demo.application.mymvvmdemo.ui.test02.IWeather
import com.demo.application.mymvvmdemo.ui.test02.StubWeather
import com.demo.application.mymvvmdemo.ui.test02.Umbrella
import com.demo.application.mymvvmdemo.ui.test03.Order
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

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

    @Test
    fun testPriceWithMockito(){
        val umbrella = Umbrella()
        //1. mock() 建立模擬物件
        val weather = Mockito.mock(IWeather::class.java)
        //2. 模擬回傳值
        Mockito.`when`(weather.isSunny()).thenReturn(false)

        val actual = umbrella.totalPrice(weather,3,100)
        val expected = 270
        Assert.assertEquals(expected,actual)
    }
}