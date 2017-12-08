package com.chenlittleping.anotherone_kotlin.net.bean.detail.movie

import com.chenlittleping.anotherone_kotlin.net.bean.home.Author


/**
 * 影视数据
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-08 14:45
 *
 */
class MovieData {
    var id = ""
    var movie_id = ""
    var title = ""
    var content = ""
    var sort = ""
    var praisenum = 0
    var input_date = ""
    var story_type = ""
    var summary = ""
    var audio = ""
    var anchor = ""
    var copyright = ""
    var audio_duration = ""
    var user: Author? = null
    var charge_edt = ""
    var editor_email = ""
    var author_list:List<Author>? = null
}