package com.chenlittleping.anotherone_kotlin.home.one.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.chenlittleping.anotherone_kotlin.view.recyclerview.IBinder
import com.chenlittleping.anotherone_kotlin.view.recyclerview.ViewHolder
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_one_story.view.*


/**
 * 文章
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-27 15:18
 *
 */
class StoryBinder(inflater: LayoutInflater, parent: ViewGroup?) :
        ViewHolder(inflater, parent, R.layout.item_one_story),
        IBinder<Content> {

    override fun bindViewHolder(holder: ViewHolder, item: Content) {
        Glide.with(holder!!.itemView.context)
                .load(item.img_url)
                .asBitmap().into(holder.itemView.story_pic)
        var title = when (item.category) {
            1 -> "- ONE STORY -"
            2 -> "- 连载 -"
            3 -> "- 问答 -"
            else -> ""
        }
        var author = when(item.category) {
            3 -> "网友答"
            else -> "文/" + item.author?.user_name
        }
        holder.itemView.story_type.text = title
        holder.itemView.story_title.text = item.title
        holder.itemView.story_author.text = author
        holder.itemView.story_desc.text = item.forward
        holder.itemView.date.text = item.post_date.split(" ")[0]
        holder.itemView.like.text = item.like_count?.toString()
    }
}