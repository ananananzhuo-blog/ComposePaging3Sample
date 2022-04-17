package com.ananananzhuo.composepaging3sample

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * author  :mayong
 * function:
 * date    :2022/4/17
 **/
class App: Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var app:Context
    }
    override fun onCreate() {
        super.onCreate()
        app = this.applicationContext
    }
}