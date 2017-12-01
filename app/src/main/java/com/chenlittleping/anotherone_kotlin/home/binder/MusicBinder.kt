package com.chenlittleping.anotherone_kotlin.home.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.chenlittleping.anotherone_kotlin.view.recyclerview.IBinder
import com.chenlittleping.anotherone_kotlin.view.recyclerview.ViewHolder
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_one_music.view.*


/**
 * 音乐
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-27 11:30
 *
 */
class MusicBinder(inflater: LayoutInflater, parent: ViewGroup?):
        ViewHolder(inflater, parent, R.layout.item_one_music), IBinder<Content> {

    override fun bindViewHolder(holder: ViewHolder, item: Content) {
        Glide.with(holder!!.itemView.context)
                .load(item.img_url)
                .asBitmap().into(holder.itemView.music_pic)
        holder.itemView.music_type.text = "- 音乐 -"
        holder.itemView.music_title.text = item.title
        holder.itemView.music_author.text = "文/" + item.author?.user_name
        holder.itemView.music_name.text = item.subtitle + " · " + item.audio_author + " | " + item.audio_album
        holder.itemView.music_desc.text = item.forward
        holder.itemView.date.text = item.post_date.split(" ")[0]
        holder.itemView.like.text = item.like_count?.toString()
    }
}