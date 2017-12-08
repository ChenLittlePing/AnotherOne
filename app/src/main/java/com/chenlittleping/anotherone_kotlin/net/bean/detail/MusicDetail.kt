package com.chenlittleping.anotherone_kotlin.net.bean.detail

import com.chenlittleping.anotherone_kotlin.net.bean.home.Author


/**
 * 音乐详情
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-08 15:51
 *
 */
class MusicDetail {
    
    var id = ""
    var title = ""
    var cover = ""
    var isfirst = ""
    var story_title = ""
    var story = ""
    var lyric = ""
    var info = ""
    var platform = ""
    var music_id = ""
    var charge_edt = ""
    var related_to = ""
    var web_url = ""
    var praisenum = 0
    var hide_flag = ""
    var sort = ""
    var maketime = ""
    var last_update_date = ""
    var read_num = ""
    var story_summary = ""
    var audio = ""
    var anchor = ""
    var editor_email = ""
    var related_musics = ""
    var album = ""
    var start_video = ""
    var media_type = ""
    var copyright = ""
    var audio_duration = ""
    var author: Author? = null
    var story_author: Author? = null
    var author_list: List<Author>? = null
    var feeds_cover = ""
    var next_id = 0
    var previous_id = ""
    var sharenum = 0
    var commentnum = 0
}