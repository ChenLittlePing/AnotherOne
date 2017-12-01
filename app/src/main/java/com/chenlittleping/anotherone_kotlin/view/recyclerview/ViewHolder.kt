package com.chenlittleping.anotherone_kotlin.view.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


/**
 * 列表视图容器
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-26 20:50
 *
 */
open class ViewHolder : RecyclerView.ViewHolder {
    constructor(inflater: LayoutInflater, parent: ViewGroup?, layout: Int):
            super(inflater.inflate(layout, parent, false))
}