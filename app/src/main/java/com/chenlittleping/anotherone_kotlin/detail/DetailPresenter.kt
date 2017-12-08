package com.chenlittleping.anotherone_kotlin.detail

import com.chenlittleping.anotherone_kotlin.net.api.detail.DetailRequest
import com.chenlittleping.anotherone_kotlin.net.bean.detail.MusicDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.SerialDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.StoryDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.movie.MovieDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.question.QuestionDetail
import com.chenlittleping.net.IOut
import rx.subscriptions.CompositeSubscription


/**
 * 文章/问答/连载mvp接口实现
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-08 10:37
 *
 */
class DetailPresenter(override var view: DetailContract.View) : DetailContract.Presenter {

    private var subscription = CompositeSubscription()

    override fun getEssayDetail(item_id: String, id: String) {
        subscription.add(DetailRequest().getEssayDetail(item_id, id, object : IOut<StoryDetail?> {
            override fun success(r: StoryDetail?) {
                view.updateEssayDetail(r, null)
            }

            override fun error(info: String) {
                view.updateEssayDetail(null, info)
            }
        }))
    }

    override fun getAnswerDetail(item_id: String, id: String) {
        subscription.add(DetailRequest().getAnswerDetail(item_id, id, object : IOut<QuestionDetail?> {
            override fun success(r: QuestionDetail?) {
                view.updateAnswerDetail(r, null)
            }

            override fun error(info: String) {
                view.updateAnswerDetail(null, info)
            }
        }))
    }

    override fun getSerialDetail(item_id: String, id: String) {
        subscription.add(DetailRequest().getSerialDetail(item_id, id, object : IOut<SerialDetail?> {
            override fun success(r: SerialDetail?) {
                view.updateSerialDetail(r, null)
            }

            override fun error(info: String) {
                view.updateSerialDetail(null, info)
            }
        }))
    }

    override fun getMovieDetail(item_id: String) {
        subscription.add(DetailRequest().getMovieDetail(item_id, object : IOut<MovieDetail?> {
            override fun success(r: MovieDetail?) {
                view.updateMovieDetail(r, null)
            }

            override fun error(info: String) {
                view.updateMovieDetail(null, info)
            }
        }))
    }

    override fun getMusicDetail(item_id: String) {
        subscription.add(DetailRequest().getMusicDetail(item_id, object : IOut<MusicDetail?> {
            override fun success(r: MusicDetail?) {
                view.updateMusicDetail(r, null)
            }

            override fun error(info: String) {
                view.updateMusicDetail(null, info)
            }
        }))
    }

    override fun detach() {
        if (subscription != null && subscription.hasSubscriptions()) {
            subscription.unsubscribe()
        }
    }

}