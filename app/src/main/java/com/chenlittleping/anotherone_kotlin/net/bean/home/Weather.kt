package com.chenlittleping.anotherone_kotlin.net.bean.home

import com.google.gson.annotations.SerializedName


/**
 * 天气
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-23 14:10
 *
 */
class Weather {
    @SerializedName("city_name")
    var cityName: String? = null       //"地球"
    var date: String? = null           //"2017-01-08"
    var temperature: String? = null    //"-275"
    var humidity: String? = null       //"120"
    var climate: String? = null        //"对流层"
    @SerializedName("wind_direction")
    var windDirection: String? = null //"一阵妖风"
    var hurricane: String? = null      //"36级"
}