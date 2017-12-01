package com.chenlittleping.anotherone_kotlin.home

import com.chenlittleping.anotherone_kotlin.net.api.home.HomeRequest
import com.chenlittleping.anotherone_kotlin.net.bean.home.HomeData
import com.chenlittleping.net.IOut
import rx.subscriptions.CompositeSubscription


/**
 * Home MVP Presenter
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-26 13:20
 *
 */
class HomePresenter(override var view: HomeContract.View): HomeContract.Presenter {
    private var subscription = CompositeSubscription()

    override fun detach() {
        if (subscription.hasSubscriptions()) {
            subscription.unsubscribe()
        }
    }

    override fun getIdList() {
        subscription.add(HomeRequest().getIdList(object: IOut<List<String>?> {
            override fun success(r: List<String>?) {
                view.updateIdList(r, null)
            }

            override fun error(info: String) {
                view.updateIdList(null, info)
            }
        }))
    }

    override fun getOneList(id: String) {
        subscription.add(HomeRequest().getOneList(id, object: IOut<HomeData?> {
            override fun success(r: HomeData?) {
                view.updateOneList(r, null)
            }

            override fun error(info: String) {
                view.updateOneList(null, info)
            }
        }))
    }
}