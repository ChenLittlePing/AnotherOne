package com.chenlittleping.anotherone_kotlin.net

import com.chenlittleping.net.Request

/**
 * 基础请求数据封装
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 20:42
 *
 */
open class BaseRequest<T>(s: Class<T>): Request<T>(s) {
    private val KEY_UUID = "uuid"
    private val VALUE_UUID = "ffffffff-a90e-706a-63f7-ccf973aae5ee"

    private val KEY_PLATEFORM = "platform"
    private val VALUE_PLATEFORM = "android"

    private val KEY_VERSION = "version"
    private val VALUE_VERSION = "4.3.4"

    private val KEY_CHANNEL = "channel"
    private val VALUE_CHANLE = "wdj"

    override fun getBaseUrl(): String {
        return "http://v3.wufazhuce.com:8000/api/"
    }

    override fun addDefData() {
        addData(KEY_UUID, VALUE_UUID)
        addData(KEY_PLATEFORM, VALUE_PLATEFORM)
        addData(KEY_VERSION, VALUE_VERSION)
        addData(KEY_CHANNEL, VALUE_CHANLE)
    }
}