package com.chenlittleping.anotherone_kotlin.base

import android.os.Build
import android.os.Bundle
import android.view.View
import com.aitangba.swipeback.SwipeBackActivity
import kotlinx.android.synthetic.main.title_activity.*


/**
 * 页面基类
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 14:37
 *
 */
open abstract class BaseActivity : SwipeBackActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initStatusBar()
        initBackBtn()
        getData()
        initUI()
    }

    private fun initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    private fun initBackBtn() {
        back?.setOnClickListener({
            if (!onBack()) {
                finish()
            }
        })
    }

    protected fun setTitle(text: String) {
        title_text?.text = text
    }

    open protected fun onBack(): Boolean {
        return false
    }

    protected abstract fun getLayout() : Int
    protected open fun getData(){}
    protected abstract fun initUI()
}