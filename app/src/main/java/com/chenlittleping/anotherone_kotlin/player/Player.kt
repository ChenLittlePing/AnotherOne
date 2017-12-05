package com.chenlittleping.anotherone_kotlin.player

import android.media.MediaPlayer
import android.util.Log
import org.greenrobot.eventbus.EventBus
import java.io.IOException


/**
 * 播放器
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-04 17:14
 *
 */
object Player: IPlayer {

    private val TAG = "Player"

    private var player: MediaPlayer = MediaPlayer()

    private var currentUrl: String? = null

    private var playStatus: IPlayStatus? = null

    init {
        player.setOnCompletionListener {onCompletion(it)}
        player.setOnPreparedListener({onPrepare(it)})
    }

    private fun onCompletion(mp: MediaPlayer) {
        notifyPlayStatus(mp.isPlaying)
    }

    override fun play(url: String?): Boolean {
        if (url == null) return false
        if (currentUrl.equals(url)) {
            play()
            return true
        }
        currentUrl = url
        try {
            player.reset()
            player.setDataSource(url)
            player.prepareAsync()
        } catch (e: IOException) {
            Log.e(TAG, "play: ", e)
            notifyPlayStatus(false)
            return false
        }
        return true
    }

    override fun pause(): Boolean {
        if (player.isPlaying) {
            player.pause()
            notifyPlayStatus(false)
        }
        return true
    }

    override fun play() {
        if (!player.isPlaying) {
            player.start()
            notifyPlayStatus(true)
        }
    }

    private fun onPrepare(mp: MediaPlayer) {
        mp.start()
        notifyPlayStatus(mp.isPlaying)
    }

    override fun isPlaying(): Boolean {
        return player.isPlaying
    }

    override fun release() {
        player.reset()
        player.release()
    }

    override fun getMusicUrl(): String? {
        return currentUrl
    }

    private fun notifyPlayStatus(playing: Boolean) {
        var event = PlayerEvent()
        event.isPlaying = playing
        event.url = currentUrl
        EventBus.getDefault().post(event)
    }
}