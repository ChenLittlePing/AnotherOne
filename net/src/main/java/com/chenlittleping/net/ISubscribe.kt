package com.chenlittleping.net

import rx.Observable
import rx.Subscription


/**
 * 请求接口定义
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 18:18
 *
 */
interface ISubscribe<R> {
    fun perform(o : Observable<out IResult<R?>>, out : IOut<R?>) : Subscription
}