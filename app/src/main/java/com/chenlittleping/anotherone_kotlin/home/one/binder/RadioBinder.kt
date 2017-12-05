package com.chenlittleping.anotherone_kotlin.home.one.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.chenlittleping.anotherone_kotlin.player.IPlayer
import com.chenlittleping.anotherone_kotlin.player.Player
import com.chenlittleping.anotherone_kotlin.view.recyclerview.IBinder
import com.chenlittleping.anotherone_kotlin.view.recyclerview.ViewHolder
import kotlinx.android.synthetic.main.item_night_radio.view.*


/**
 * 深夜电台
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-05 14:23
 *
 */
class RadioBinder(inflater: LayoutInflater, parent: ViewGroup?):
        ViewHolder(inflater, parent, R.layout.item_night_radio), IBinder<Content> {

    private var url: String? = null
    private var player : IPlayer? =  null

    override fun bindViewHolder(holder: ViewHolder, item: Content) {
        Glide.with(holder!!.itemView.context)
                .load(item.img_url)
                .asBitmap().into(holder.itemView.radio_pic)
        if (item.is_regular == 1) {
            holder.itemView.radio_volume.text = item.volume
            holder.itemView.radio_title.text = item.title
            holder.itemView.author_name.text = item.author?.user_name
            Glide.with(holder.itemView.context)
                    .load(item.author?.web_url)
                    .into(holder.itemView.author_img)
        } else {
            holder.itemView.fm_logo.visibility = View.GONE
            holder.itemView.radio_center_title.text = item.title
            holder.itemView.radio_mask.visibility = View.GONE
        }
        holder.itemView.like.text = item.like_count?.toString()
        setPlayClick(holder, item)
    }

    private fun setPlayClick(holder: ViewHolder, item: Content) {
        holder.itemView.setOnClickListener({
            if (player == null) {
                player = Player
            }
            if (!player!!.isPlaying()) {
                url = item.audio_url
                if (player!!.play(item.audio_url)) {
                    holder.itemView.regulate_play.setImageResource(R.mipmap.ic_pause)
                }
            } else {
                if (player!!.getMusicUrl().equals(url)) {
                    if (player!!.pause()) {
                        holder.itemView.regulate_play.setImageResource(R.mipmap.ic_play)
                    }
                } else {
                    player!!.play(item.audio_url)
                }
            }
        })
    }
}