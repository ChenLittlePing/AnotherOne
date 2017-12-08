package com.chenlittleping.anotherone_kotlin.net.bean.home

import android.os.Parcel
import android.os.Parcelable


/**
 * 列表内容
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-26 14:21
 *
 */
class Content(): Parcelable {
    var id = ""
    var category = 0
    var display_category = 0
    var is_regular = 2
    var item_id = ""
    var title = ""
    var forward = ""
    var img_url = ""
    var like_count = 0
    var post_date = ""
    var last_update_date = ""
    var video_url = ""
    var audio_url = ""
    var audio_author = ""
    var audio_album = ""
    var audio_platform = 0
    var start_video = ""
    var volume = ""
    var pic_info = ""
    var words_info = ""
    var subtitle = ""
    var number = 0
    var serial_id = 0
    var movie_story_id = 0
    var ad_id = 0
    var content_id = ""
    var content_type = ""
    var content_bgcolor = "#FFFFFF"

    var author : Author? = null
    var serial_list : List<String>? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        category = parcel.readInt()
        display_category = parcel.readInt()
        is_regular = parcel.readInt()
        item_id = parcel.readString()
        title = parcel.readString()
        forward = parcel.readString()
        img_url = parcel.readString()
        like_count = parcel.readInt()
        post_date = parcel.readString()
        last_update_date = parcel.readString()
        video_url = parcel.readString()
        audio_url = parcel.readString()
        audio_author = parcel.readString()
        audio_album = parcel.readString()
        audio_platform = parcel.readInt()
        start_video = parcel.readString()
        volume = parcel.readString()
        pic_info = parcel.readString()
        words_info = parcel.readString()
        subtitle = parcel.readString()
        number = parcel.readInt()
        serial_id = parcel.readInt()
        movie_story_id = parcel.readInt()
        ad_id = parcel.readInt()
        content_id = parcel.readString()
        content_type = parcel.readString()
        content_bgcolor = parcel.readString()
        serial_list = parcel.createStringArrayList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(category)
        parcel.writeInt(display_category)
        parcel.writeInt(is_regular)
        parcel.writeString(item_id)
        parcel.writeString(title)
        parcel.writeString(forward)
        parcel.writeString(img_url)
        parcel.writeInt(like_count)
        parcel.writeString(post_date)
        parcel.writeString(last_update_date)
        parcel.writeString(video_url)
        parcel.writeString(audio_url)
        parcel.writeString(audio_author)
        parcel.writeString(audio_album)
        parcel.writeInt(audio_platform)
        parcel.writeString(start_video)
        parcel.writeString(volume)
        parcel.writeString(pic_info)
        parcel.writeString(words_info)
        parcel.writeString(subtitle)
        parcel.writeInt(number)
        parcel.writeInt(serial_id)
        parcel.writeInt(movie_story_id)
        parcel.writeInt(ad_id)
        parcel.writeString(content_id)
        parcel.writeString(content_type)
        parcel.writeString(content_bgcolor)
        parcel.writeStringList(serial_list)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Content> {
        override fun createFromParcel(parcel: Parcel): Content {
            return Content(parcel)
        }

        override fun newArray(size: Int): Array<Content?> {
            return arrayOfNulls(size)
        }
    }
}