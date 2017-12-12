package com.chenlittleping.anotherone_kotlin.main

import android.app.Application
import com.coder.zzq.smartshow.toast.SmartToast


/**
 * Application
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-12 08:53
 *
 */
class MainApp: Application() {
    override fun onCreate() {
        super.onCreate()
        SmartToast.plainToast(this)
    }
}