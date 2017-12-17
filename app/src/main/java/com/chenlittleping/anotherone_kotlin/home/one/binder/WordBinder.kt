package com.chenlittleping.anotherone_kotlin.home.one.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.chenlittleping.anotherone_kotlin.view.recyclerview.IBinder
import com.chenlittleping.anotherone_kotlin.view.recyclerview.ViewHolder
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_one_word.view.*


/**
 * 一句
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-27 11:30
 *
 */
class WordBinder(inflater: LayoutInflater, parent: ViewGroup?):
        ViewHolder(inflater, parent, R.layout.item_one_word), IBinder<Content> {

    override fun bindViewHolder(holder: ViewHolder, item: Content) {
        Glide.with(holder!!.itemView.context)
                .load(item.img_url)
                .crossFade()
                .into(holder.itemView.word_pic)

        holder.itemView.apply {
            word_info.text = item.title + " | " + item.pic_info
            word_desc.text = item.forward
            word_name.text = item.words_info
            date.text = item.post_date.split(" ")[0]
            like.text = item.like_count?.toString()
        }
    }
}