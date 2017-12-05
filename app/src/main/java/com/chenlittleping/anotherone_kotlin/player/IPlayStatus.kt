package com.chenlittleping.anotherone_kotlin.player


/**
 * 播放状态反馈接口
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-04 20:34
 *
 */
interface IPlayStatus {
    fun playStatus(playing: Boolean)
}