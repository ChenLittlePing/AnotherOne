package com.chenlittleping.anotherone_kotlin.net.bean.detail.question

import com.chenlittleping.anotherone_kotlin.net.bean.home.Author


/**
 * 问答详情
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-08 11:09
 *
 */
class QuestionDetail {
    var answer_title = ""
    var read_num = ""
    var audio_duration = ""
    var author_list: List<Author>? = null
    var answerer: Answerer? = null
    var question_id = ""
    var guide_word = ""
    var recommend_flag = ""
    var sharenum = 0
    var copyright = ""
    var answer_content = ""
    var start_video = ""
    var cover_media_type = ""
    var cover = ""
    var next_id = 0
    var anchor = ""
    var audio = ""
    var charge_edt = ""
    var praisenum = 0
    var question_title = ""
    var commentnum = 0
    var web_url = ""
    var previous_id = ""
    var asker_list: List<Asker>? = null
    var question_content = ""
    var content_bgcolor = ""
    var charge_email = ""
    var asker: Asker? = null
    var cover_media_file = ""
}