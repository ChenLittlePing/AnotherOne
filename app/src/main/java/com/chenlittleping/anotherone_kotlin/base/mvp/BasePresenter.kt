package com.chenlittleping.anotherone_kotlin.base.mvp


/**
 * MVP Presenter 基类
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-26 12:36
 *
 */
interface BasePresenter<V> {
    var view : V
    fun attach() {}
    fun detach() {}
}