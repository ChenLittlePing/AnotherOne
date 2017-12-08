package com.chenlittleping.anotherone_kotlin.detail

import com.chenlittleping.anotherone_kotlin.base.mvp.BasePresenter
import com.chenlittleping.anotherone_kotlin.base.mvp.BaseView
import com.chenlittleping.anotherone_kotlin.net.bean.detail.MusicDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.SerialDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.StoryDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.movie.MovieDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.question.QuestionDetail


/**
 * 文章/问答/连载mvp接口
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-08 10:34
 *
 */
interface DetailContract {
    interface View: BaseView<Presenter> {
        fun updateEssayDetail(detail: StoryDetail?, error: String?)
        fun updateAnswerDetail(detail: QuestionDetail?, error: String?)
        fun updateSerialDetail(detail: SerialDetail?, error: String?)
        fun updateMovieDetail(detail: MovieDetail?, error: String?)
        fun updateMusicDetail(detail: MusicDetail?, error: String?)
    }

    interface Presenter: BasePresenter<View> {
        fun getEssayDetail(item_id: String, id: String)
        fun getAnswerDetail(item_id: String, id: String)
        fun getSerialDetail(item_id: String, id: String)
        fun getMovieDetail(item_id: String)
        fun getMusicDetail(item_id: String)
    }
}