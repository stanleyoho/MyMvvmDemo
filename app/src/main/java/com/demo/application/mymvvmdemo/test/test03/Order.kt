package com.demo.application.mymvvmdemo.test.test03

import com.demo.application.mymvvmdemo.test.test02.StubWeather
import com.demo.application.mymvvmdemo.test.test02.Umbrella

class Order {
    fun insertOrder(email:String, quantity:Int, price:Int, emailUtil:IEmailUtil){
        val weather = StubWeather()
        val umbrella = Umbrella()
        umbrella.totalPrice(weather,quantity,price)
        //new order

        //send email
        emailUtil.sendCustomer(email)
    }
}