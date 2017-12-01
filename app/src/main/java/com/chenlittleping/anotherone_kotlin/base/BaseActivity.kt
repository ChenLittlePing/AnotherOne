package com.chenlittleping.anotherone_kotlin.base

import android.os.Build
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View


/**
 * 页面基类
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 14:37
 *
 */
open abstract class BaseActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initStatusBar()
        getData()
        initUI()
    }

    private fun initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    protected abstract fun getLayout() : Int
    protected open fun getData(){}
    protected abstract fun initUI()
}