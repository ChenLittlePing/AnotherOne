package com.chenlittleping.anotherone_kotlin.net

import com.chenlittleping.net.IResult
import com.google.gson.annotations.SerializedName


/**
 * 网络数据解析封装
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-23 10:55
 *
 */
class NetResult<T>: IResult<T> {

    @SerializedName("data")
    private var d: T? = null

    private var res: Int = -1

    override fun getData(): T? {
        return d
    }

    override fun isSuccess(): Boolean {
        return res == 0
    }
}