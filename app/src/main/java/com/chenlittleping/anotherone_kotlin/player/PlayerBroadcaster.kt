package com.chenlittleping.anotherone_kotlin.player

import com.chenlittleping.anotherone_kotlin.player.event.DurationEvent
import com.chenlittleping.anotherone_kotlin.player.event.PlayingEvent
import org.greenrobot.eventbus.EventBus


/**
 * 播放器信息广播器
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-06 13:39
 *
 */
object PlayerBroadcaster {

    fun postPlayStatus(playing: Boolean, song: Song?) {
        var event = PlayingEvent()
        event.isPlaying = playing
        event.url = song?.url
        EventBus.getDefault().post(event)
    }

    fun postDurationStatus(song: Song?, duration: Int, progress: Int) {
        var event = DurationEvent()
        event.song = song
        event.duration = duration
        event.progress = progress
        EventBus.getDefault().post(event)
    }
}