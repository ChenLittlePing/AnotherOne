package com.chenlittleping.net

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.adapter.rxjava.HttpException
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


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
                                    out.error(r.getMessage())
                                }
                            }
                        },
                        {
                            e ->
                            when (e) {
                                is UnknownHostException,
                                is SocketException -> out.error("网络连接异常")
                                is HttpException -> out.error("网络异常")
                                is JsonParseException,
                                is JSONException,
                                is ParseException -> out.error("数据解析出错")
                                is ConnectException -> out.error("网络链接错误")
                                is SocketTimeoutException -> out.error("网络超时")
                                else -> out.error("未知错误")
                            }
                        }
                )
    }
}