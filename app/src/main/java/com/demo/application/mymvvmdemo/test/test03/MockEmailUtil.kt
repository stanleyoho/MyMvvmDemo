package com.demo.application.mymvvmdemo.test.test03

class MockEmailUtil:IEmailUtil {
    // receiveEmail 用來記錄由sendCustomer傳進來的Email
    var receiveEmail:String? = null
    override fun sendCustomer(email: String) {
        receiveEmail = email
    }
}