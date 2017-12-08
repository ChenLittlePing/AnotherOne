package com.chenlittleping.anotherone_kotlin.net.bean.detail

import com.chenlittleping.anotherone_kotlin.net.bean.home.Author
import com.google.gson.annotations.SerializedName


/**
 * 阅读详情
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-06 15:48
 *
 */
class StoryDetail {
    @SerializedName("content_id")
    var contentId = ""
    @SerializedName("hp_title")
    var hpTitle = ""
    @SerializedName("sub_title")
    var subTitle = ""
    @SerializedName("hp_author")
    var hpAuthor = ""
    @SerializedName("auth_it")
    var authIt = ""
    @SerializedName("hp_author_introduce")
    var hpAuthorIntroduce = ""
    @SerializedName("hp_content")
    var hpContent = ""
    @SerializedName("hp_makettime")
    var hpMakettime = ""
    @SerializedName("hide_flag")
    var hideFlag = ""
    @SerializedName("wb_name")
    var wbName = ""
    @SerializedName("wb_img_url")
    var wbImgUrl = ""
    @SerializedName("last_update_date")
    var lastUpdateDate = ""
    @SerializedName("web_url")
    var webUrl = ""
    @SerializedName("guide_word")
    var guideWord = ""
    var audio = ""
    var anchor = ""
    @SerializedName("editor_email")
    var editorEmail = ""
    @SerializedName("top_media_type")
    var topMediaType = ""
    @SerializedName("top_media_file")
    var topMediaFile = ""
    @SerializedName("top_media_image")
    var topMediaImage = ""
    @SerializedName("start_video")
    var startVideo = ""
    var copyright = ""
    @SerializedName("audio_duration")
    var audioDuration = ""
    var cover = ""
    var maketime = ""
    @SerializedName("next_id")
    var nextId = ""
    @SerializedName("previous_id")
    var previousId = ""
    var praisenum = 0
    var sharenum = 0
    var commentnum = 0
    @SerializedName("author_list")
    var authors: List<Author>? = null

}