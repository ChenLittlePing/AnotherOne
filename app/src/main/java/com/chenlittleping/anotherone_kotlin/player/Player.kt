package com.chenlittleping.anotherone_kotlin.player

import android.media.MediaPlayer
import android.os.Message
import android.util.Log
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

    private val HANDLER_MSG_DURATION = 1

    private var player: MediaPlayer = MediaPlayer()

    private var currentSong: Song? = null

    init {
        player.setOnCompletionListener {onCompletion(it)}
        player.setOnPreparedListener({onPrepare(it)})
    }

    private fun onCompletion(mp: MediaPlayer) {
        notifyPlayStatus(mp.isPlaying)
    }

    override fun play(song: Song?, replay: Boolean): Boolean {
        if (song?.url == null) return false
        if (!replay) {
            if (currentSong?.url.equals(song?.url)) {
                play()
                return true
            }
        }
        currentSong = song
        try {
            player.reset()
            player.setDataSource(song?.url)
            player.prepareAsync()
        } catch (e: IOException) {
            Log.e(TAG, "play: ", e)
            notifyPlayStatus(false)
            return false
        }
        return true
    }

    override fun replay() {
        play(currentSong, true)
    }

    override fun play() {
        if (!currentSong?.url.isNullOrEmpty()) {
            if (!player.isPlaying) {
                player.start()
                notifyPlayStatus(true)
                handler.sendEmptyMessage(HANDLER_MSG_DURATION)
            }
        }
    }

    override fun pause(): Boolean {
        if (player.isPlaying) {
            player.pause()
            notifyPlayStatus(false)
        }
        return true
    }

    override fun seek(position: Int) {
        if (player != null) {
            player.seekTo(position)
        }
    }

    private fun onPrepare(mp: MediaPlayer) {
        mp.start()
        notifyPlayStatus(mp.isPlaying)
        handler.sendEmptyMessage(HANDLER_MSG_DURATION)
    }

    override fun isPlaying(): Boolean {
        return player.isPlaying
    }

    override fun release() {
        player.reset()
        player.release()
    }

    override fun getSong(): Song? {
        return currentSong
    }

    private fun notifyPlayStatus(playing: Boolean) {
        PlayerBroadcaster.postPlayStatus(playing, currentSong)
    }

    private object handler: android.os.Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                HANDLER_MSG_DURATION -> {
                    PlayerBroadcaster.postDurationStatus(currentSong, player.duration, player.currentPosition)
                    if (player.isPlaying) {
                        handler.sendEmptyMessageDelayed(HANDLER_MSG_DURATION, 500)
                    }
                }
            }
        }
    }
}