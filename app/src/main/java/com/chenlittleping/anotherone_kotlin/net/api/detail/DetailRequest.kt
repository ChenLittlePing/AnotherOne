package com.chenlittleping.anotherone_kotlin.net.api.detail

import com.chenlittleping.anotherone_kotlin.net.BaseRequest
import com.chenlittleping.anotherone_kotlin.net.bean.detail.MusicDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.SerialDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.StoryDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.movie.MovieDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.question.QuestionDetail
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
class DetailRequest : BaseRequest<DetailService> {

    constructor():super(DetailService::class.java)

    fun getEssayDetail(item_id : String, id: String, out : IOut<StoryDetail?>) : Subscription {
        addData("source_id", id)
        addData("source", "summary")
        return Subscribe<StoryDetail>().perform(service.getEssayDetail(item_id, buildQueryMap()), out)
    }

    fun getAnswerDetail(item_id : String, id: String, out : IOut<QuestionDetail?>) : Subscription {
        addData("source_id", id)
        addData("source", "summary")
        return Subscribe<QuestionDetail>().perform(service.getAnswerDetail(item_id, buildQueryMap()), out)
    }

    fun getSerialDetail(item_id : String, id: String, out : IOut<SerialDetail?>) : Subscription {
        addData("source_id", id)
        addData("source", "summary")
        return Subscribe<SerialDetail>().perform(service.getSerialDetail(item_id, buildQueryMap()), out)
    }

    fun getMovieDetail(item_id : String, out : IOut<MovieDetail?>) : Subscription {
        return Subscribe<MovieDetail>().perform(service.getMovieDetail(item_id, buildQueryMap()), out)
    }

    fun getMusicDetail(item_id : String, out : IOut<MusicDetail?>) : Subscription {
        return Subscribe<MusicDetail>().perform(service.getMusicDetail(item_id, buildQueryMap()), out)
    }
}