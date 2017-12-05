package com.chenlittleping.net


/**
 * 请求返回数据接口
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-23 10:50
 *
 */
interface IResult<T> {
    fun getMessage(): String
    fun isSuccess() : Boolean
    fun getData() : T?
}