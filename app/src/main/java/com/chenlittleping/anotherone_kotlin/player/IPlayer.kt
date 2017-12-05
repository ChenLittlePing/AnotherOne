package com.chenlittleping.anotherone_kotlin.player


/**
 * 播放器接口
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-04 17:14
 *
 */
interface IPlayer {
    fun play(url: String?): Boolean
    fun pause(): Boolean
    fun play()
    fun isPlaying(): Boolean
    fun getMusicUrl(): String?
    fun release()
}