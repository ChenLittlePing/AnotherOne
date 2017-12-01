package com.chenlittleping.net


/**
 * 网络请求结果返回接口
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 18:21
 *
 */
interface IOut<R> {
    fun success(r : R?)
    fun error(info : String)
}