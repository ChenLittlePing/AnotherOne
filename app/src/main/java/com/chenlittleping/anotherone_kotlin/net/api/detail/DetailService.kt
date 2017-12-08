package com.chenlittleping.anotherone_kotlin.net.api.detail

import com.chenlittleping.anotherone_kotlin.net.NetResult
import com.chenlittleping.anotherone_kotlin.net.bean.detail.MusicDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.SerialDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.StoryDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.movie.MovieDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.question.QuestionDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import rx.Observable


/**
 * 详情接口
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-06 15:58
 *
 */
interface DetailService {
    @GET("essay/{item_id}")
    fun getEssayDetail(@Path("item_id") id : String, @QueryMap param: Map<String, String>): Observable<NetResult<StoryDetail?>>

    @GET("question/{item_id}")
    fun getAnswerDetail(@Path("item_id") id : String, @QueryMap param: Map<String, String>): Observable<NetResult<QuestionDetail?>>

    @GET("serialcontent/{item_id}")
    fun getSerialDetail(@Path("item_id") id : String, @QueryMap param: Map<String, String>): Observable<NetResult<SerialDetail?>>

    @GET("movie/{item_id}/story/1/0")
    fun getMovieDetail(@Path("item_id") id : String, @QueryMap param: Map<String, String>): Observable<NetResult<MovieDetail?>>

    @GET("music/detail/{item_id}")
    fun getMusicDetail(@Path("item_id") id : String, @QueryMap param: Map<String, String>): Observable<NetResult<MusicDetail?>>
}