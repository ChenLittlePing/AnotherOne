package com.chenlittleping.anotherone_kotlin.home.one

import com.chenlittleping.anotherone_kotlin.base.mvp.BasePresenter
import com.chenlittleping.anotherone_kotlin.base.mvp.BaseView
import com.chenlittleping.anotherone_kotlin.net.bean.home.HomeData


/**
 * 首页逻辑接口定义
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-26 12:43
 *
 */
interface OneContract {
    interface View : BaseView<Presenter> {
        fun updateOneList(data : HomeData?, error : String?)
    }

    interface Presenter : BasePresenter<View> {
        fun getOneList(id : String)
    }
}