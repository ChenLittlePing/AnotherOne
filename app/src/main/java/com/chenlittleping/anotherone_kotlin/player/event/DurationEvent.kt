package com.chenlittleping.anotherone_kotlin.player.event

import com.chenlittleping.anotherone_kotlin.player.Song


/**
 * 播放时间更新时间
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-06 08:40
 *
 */
class DurationEvent {
    var song: Song? = Song()
    var duration = 0
    var progress = 0
}