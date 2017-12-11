package com.chenlittleping.anotherone_kotlin.net.api.home

import com.chenlittleping.anotherone_kotlin.net.NetResult
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.chenlittleping.anotherone_kotlin.net.bean.home.HomeData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import rx.Observable


/**
 * 首页接口
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 20:59
 *
 */
interface HomeService {
    @GET("onelist/idlist")
    fun getIdList(@QueryMap map : Map<String, String>) : Observable<NetResult<List<String>?>>

    @GET("onelist/{id}/0")
    fun getOneList(@Path("id") id : String, @QueryMap map : Map<String, String>) : Observable<NetResult<HomeData?>>

    @GET("channel/reading/more/0")
    fun getReadingList(@QueryMap map : Map<String, String>) : Observable<NetResult<List<Content>?>>
}