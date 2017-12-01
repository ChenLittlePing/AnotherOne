package com.chenlittleping.anotherone_kotlin.view.recyclerview


/**
 * 列表版定接口
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-27 11:26
 *
 */
interface IBinder<T> {
    fun bindViewHolder(holder: ViewHolder, item : T)
}