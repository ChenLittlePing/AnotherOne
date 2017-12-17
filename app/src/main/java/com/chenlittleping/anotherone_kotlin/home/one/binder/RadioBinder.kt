package com.chenlittleping.anotherone_kotlin.home.one.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.chenlittleping.anotherone_kotlin.player.IPlayer
import com.chenlittleping.anotherone_kotlin.player.Player
import com.chenlittleping.anotherone_kotlin.player.Song
import com.chenlittleping.anotherone_kotlin.player.event.PlayingEvent
import com.chenlittleping.anotherone_kotlin.view.recyclerview.IBinder
import com.chenlittleping.anotherone_kotlin.view.recyclerview.ViewHolder
import kotlinx.android.synthetic.main.item_night_radio.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


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
    private var player: IPlayer? =  null

    private var holder: ViewHolder? = null

    override fun bindViewHolder(holder: ViewHolder, item: Content) {
        this.holder = holder
        Glide.with(holder!!.itemView.context)
                .load(item.img_url)
                .crossFade()
                .into(holder.itemView.radio_pic)
        if (item.is_regular == 1) {
            holder.itemView.radio_volume.text = item.volume
            holder.itemView.radio_title.text = item.title
            holder.itemView.author_name.text = item.author?.user_name
            Glide.with(holder.itemView.context)
                    .load(item.author?.web_url)
                    .crossFade()
                    .into(holder.itemView.author_img)
        } else {
            holder.itemView.fm_logo.visibility = View.GONE
            holder.itemView.radio_center_title.text = item.title
            holder.itemView.radio_mask.visibility = View.GONE
        }
        holder.itemView.like.text = item.like_count?.toString()
        url = item.audio_url
        setPlayClick(holder, item)
        EventBus.getDefault().unregister(this)
        EventBus.getDefault().register(this)
        updatePlayStatus(getPlayer().isPlaying(), getPlayer().getSong()?.url)
    }

    private fun setPlayClick(holder: ViewHolder, item: Content) {
        holder.itemView.setOnClickListener({
            if (!getPlayer().isPlaying()) {
                player!!.play(createSong(item))
            } else {
                if (getPlayer().getSong()?.url.equals(url)) {
                    player!!.pause()
                } else {
                    player!!.play(createSong(item))
                }
            }
        })
    }

    private fun createSong(item: Content): Song {
        var song = Song()
        song.author = item.author!!.user_name
        song.name = item.title
        song.fromWhere = "来自ONE·一个"
        song.url = item.audio_url
        return song
    }

    private fun getPlayer(): IPlayer {
        if (player == null) {
            player = Player
        }
        return player!!
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPlayerStatusEvent(playingEvent: PlayingEvent) {
        updatePlayStatus(playingEvent.isPlaying, playingEvent.url)
    }

    private fun updatePlayStatus(playing: Boolean, url: String?) {
        if (playing && url.equals(this.url)) {
            holder?.itemView?.regulate_play?.setImageResource(R.mipmap.ic_pause)
        } else {
            holder?.itemView?.regulate_play?.setImageResource(R.mipmap.ic_play)
        }
    }
}