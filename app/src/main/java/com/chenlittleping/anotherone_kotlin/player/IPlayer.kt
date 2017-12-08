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
    fun play(song: Song?, replay: Boolean = false): Boolean
    fun play()
    fun replay()
    fun pause(): Boolean
    fun seek(position: Int)
    fun isPlaying(): Boolean
    fun getSong(): Song?
    fun release()
}