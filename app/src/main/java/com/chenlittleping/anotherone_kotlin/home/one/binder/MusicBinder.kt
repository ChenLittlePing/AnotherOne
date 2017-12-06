package com.chenlittleping.anotherone_kotlin.home.one.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.net.api.common.XiaMiRequest
import com.chenlittleping.anotherone_kotlin.net.bean.common.XiaMiUrl
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.chenlittleping.anotherone_kotlin.player.IPlayer
import com.chenlittleping.anotherone_kotlin.player.Player
import com.chenlittleping.anotherone_kotlin.player.Song
import com.chenlittleping.anotherone_kotlin.view.recyclerview.IBinder
import com.chenlittleping.anotherone_kotlin.view.recyclerview.ViewHolder
import com.chenlittleping.net.IOut
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_one_music.view.*
import java.util.regex.Pattern


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

    private var url: String? = null
    private var player : IPlayer? =  null

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

        holder.itemView.play_status.setOnClickListener({
            if (player == null) {
                player = Player
            }
            if (!player!!.isPlaying()) {
                playMusic(item)
            } else {
                if (player!!.getSong()?.url.equals(url)) {
                    player!!.pause()
                } else {
                    playMusic(item)
                }
            }
        })
    }

    private fun playMusic(item: Content) {
        if (isInteger(item.audio_url)) {
            getXiaMiMusic(item)
        } else {
            player?.play(createSong(item))
        }
    }

    private fun createSong(item: Content): Song {
        var song = Song()
        song.author = item.author!!.user_name
        song.name = item.title
        song.fromWhere = if (isInteger(item.audio_url)) "虾米音乐" else "来自ONE一个"
        song.url = item.audio_url
        return song
    }

    private fun getXiaMiMusic(item: Content) {
        var song = createSong(item)
        XiaMiRequest().getXiaMiMusic(item.audio_url.toInt(),
            object: IOut<XiaMiUrl?> {
                override fun success(r: XiaMiUrl?) {
                    url = r?.url
                    song.url = r?.url.toString()
                    player?.play(song)
                }

                override fun error(info: String) {
                    Toast.makeText(itemView.context, info, Toast.LENGTH_SHORT).show()
                }

        })
    }

    private fun isInteger(str: String): Boolean {
        val pattern = Pattern.compile("^[-\\+]?[\\d]*$")
        return pattern.matcher(str).matches()
    }
}