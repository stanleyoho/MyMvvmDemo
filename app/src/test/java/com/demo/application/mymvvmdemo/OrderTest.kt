package com.demo.application.mymvvmdemo

import com.demo.application.mymvvmdemo.test.test03.MockEmailUtil
import com.demo.application.mymvvmdemo.test.test03.Order
import org.junit.Assert
import org.junit.Test

class OrderTest {

    /*
    * 從原本的EmailUtil.sendCustomer，改成相依於介面IEmailUtil.sendCustomer就可以讓發Email這段可被測試囉。
    */
    @Test
    fun testInsertOrder(){
        val order = Order()
        val mockEmailUtil = MockEmailUtil()

        val userEmail = "someMail@gmail.com"
        order.insertOrder(userEmail, 1, 200, mockEmailUtil)
        //用mockEmailUtil.receiveEmail來驗證order.insert裡是否有呼叫IEmailUtil.setCustomer
        Assert.assertEquals(userEmail,mockEmailUtil.receiveEmail)
    }
}