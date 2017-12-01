package com.chenlittleping.anotherone_kotlin.base.mvp


/**
 * MVP View 基类
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-26 12:34
 *
 */
interface BaseView<P> {
    var presenter : P
    fun initMvp()
}