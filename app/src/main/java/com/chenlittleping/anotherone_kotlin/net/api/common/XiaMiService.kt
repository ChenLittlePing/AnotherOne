package com.chenlittleping.anotherone_kotlin.net.api.common

import com.chenlittleping.anotherone_kotlin.net.NetResult
import com.chenlittleping.anotherone_kotlin.net.bean.common.XiaMiUrl
import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable


/**
 * 虾米音乐接口
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-04 19:59
 *
 */
interface XiaMiService {
    @GET("run/")
    fun getXiaMiMusic(@QueryMap param: Map<String, String>): Observable<NetResult<XiaMiUrl?>>
}