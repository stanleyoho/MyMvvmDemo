package com.demo.application.mymvvmdemo.test.test02

class Umbrella {

    //購買雨傘計價
    fun totalPrice(weather: IWeather, quantity: Int, price: Int): Int {
        //取得是否是晴天
        val isSunny = weather.isSunny()
        //購買數量 * 價錢
        var price = quantity * price
        if (isSunny) {
            //晴天 -> 打9折
            price = (price * 0.9).toInt()
        }
        return price
    }
}