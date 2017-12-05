package com.chenlittleping.anotherone_kotlin.home.one

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.chenlittleping.anotherone_kotlin.home.one.binder.*
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.chenlittleping.anotherone_kotlin.view.recyclerview.IBinder
import com.chenlittleping.anotherone_kotlin.view.recyclerview.ViewHolder


/**
 * 主页列表适配器
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 15:33
 *
 */
class OneAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val TYPE_ONE_WORD = 1
    private val TYPE_ONE_STORY = 2
    private val TYPE_ONE_SERIAL = 3
    private val TYPE_ONE_ANSWER = 4
    private val TYPE_ONE_MUSIC = 5
    private val TYPE_ONE_MOVIE = 6
    private val TYPE_ONE_RADIO = 8

    private var mItems : List<Content>? = null

    fun bind(items : List<Content>?) {
        mItems = items
        notifyDataSetChanged()
    }

    override fun getItemCount() = mItems?.size ?: 0

    override fun getItemViewType(position: Int): Int =
        when(mItems!![position].category) {
            0 -> TYPE_ONE_WORD
            1 -> TYPE_ONE_STORY
            2 -> TYPE_ONE_SERIAL
            3 -> TYPE_ONE_ANSWER
            4 -> TYPE_ONE_MUSIC
            5 -> TYPE_ONE_MOVIE
            8 -> TYPE_ONE_RADIO
            else -> super.getItemViewType(position)
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var inflate = LayoutInflater.from(parent?.context)
        return when (viewType) {
            TYPE_ONE_WORD -> WordBinder(inflate, parent)
            TYPE_ONE_STORY -> StoryBinder(inflate, parent)
            TYPE_ONE_ANSWER -> StoryBinder(inflate, parent)
            TYPE_ONE_SERIAL -> StoryBinder(inflate, parent)
            TYPE_ONE_MOVIE -> MovieBinder(inflate, parent)
            TYPE_ONE_MUSIC -> MusicBinder(inflate, parent)
            TYPE_ONE_RADIO -> RadioBinder(inflate, parent)
            else -> StoryBinder(inflate, parent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var item = mItems!![position]
        holder as IBinder<Content>
        holder.bindViewHolder(holder, item)
    }
}