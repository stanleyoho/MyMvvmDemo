package com.demo.application.mymvvmdemo.test.test03

interface IEmailUtil {
    fun sendCustomer(email: String)
}

class EmailUtil : IEmailUtil {
    override fun sendCustomer(email: String) {
        //假裝發Email
    }
}