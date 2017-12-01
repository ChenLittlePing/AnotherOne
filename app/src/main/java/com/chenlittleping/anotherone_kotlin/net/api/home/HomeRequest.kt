package com.chenlittleping.anotherone_kotlin.net.api.home

import com.chenlittleping.anotherone_kotlin.net.BaseRequest
import com.chenlittleping.anotherone_kotlin.net.bean.home.HomeData
import com.chenlittleping.net.IOut
import com.chenlittleping.net.Subscribe
import rx.Subscription


/**
 * 首页接口请求
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 21:02
 *
 */
class HomeRequest : BaseRequest<HomeService> {
    constructor():super(HomeService::class.java)

    fun getIdList(out : IOut<List<String>?>) : Subscription {
        return Subscribe<List<String>>().perform(service.getIdList(buildQueryMap()), out)
    }

    fun getOneList(id : String, out : IOut<HomeData?>) : Subscription {
        return Subscribe<HomeData>().perform(service.getOneList(id, buildQueryMap()), out)
    }
}