package com.chenlittleping.net

import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * 接口
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 21:22
 *
 */
class Subscribe<R> : ISubscribe<R> {
    override fun perform(o: Observable<out IResult<R?>>, out: IOut<R?>): Subscription {
        return o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {r ->
                            run {
                                if (r.isSuccess()) {
                                    out.success(r.getData())
                                } else {
                                    out.error("获取数据失败")
                                }
                            }
                        },
                        {
                            e -> out.error("网络请求失败"+e.message)
                        }
                )
    }
}