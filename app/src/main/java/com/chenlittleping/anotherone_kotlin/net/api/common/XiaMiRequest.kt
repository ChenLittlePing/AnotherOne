package com.chenlittleping.anotherone_kotlin.net.api.common

import com.chenlittleping.anotherone_kotlin.net.BaseRequest
import com.chenlittleping.anotherone_kotlin.net.bean.common.XiaMiUrl
import com.chenlittleping.net.IOut
import com.chenlittleping.net.Subscribe
import rx.Subscription


/**
 * 虾米音乐请求
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-04 20:03
 *
 */
class XiaMiRequest : BaseRequest<XiaMiService> {

    constructor(): super(XiaMiService::class.java)

    override fun addDefData() {}

    override fun getBaseUrl(): String {
        return "http://xiamirun.avosapps.com/"
    }

    fun getXiaMiMusic(id: Int, out: IOut<XiaMiUrl?>): Subscription {
        addData("song", "http://www.xiami.com/song/"+id.toString())
        return Subscribe<XiaMiUrl>().perform(service.getXiaMiMusic(buildQueryMap()), out)
    }
}