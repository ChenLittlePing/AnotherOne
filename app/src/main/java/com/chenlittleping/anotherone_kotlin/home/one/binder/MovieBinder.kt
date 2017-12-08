package com.chenlittleping.anotherone_kotlin.home.one.binder

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.detail.DetailActivity
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.chenlittleping.anotherone_kotlin.view.recyclerview.IBinder
import com.chenlittleping.anotherone_kotlin.view.recyclerview.ViewHolder
import kotlinx.android.synthetic.main.item_footer.view.*
import kotlinx.android.synthetic.main.item_one_movie.view.*


/**
 * 电影
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-27 11:30
 *
 */
class MovieBinder(inflater: LayoutInflater, parent: ViewGroup?):
        ViewHolder(inflater, parent, R.layout.item_one_movie), IBinder<Content> {

    override fun bindViewHolder(holder: ViewHolder, item: Content) {
        Glide.with(holder.itemView.context)
                .load(item.img_url)
                .asBitmap().into(holder.itemView.movie_pic)
        holder.itemView.movie_type.text = "- 影视 -"
        holder.itemView.movie_title.text = item.title
        holder.itemView.movie_author.text = "文/" + item.author?.user_name
        holder.itemView.movie_desc.text = item.forward
        holder.itemView.movie_subtitle.text = "——《" + item.subtitle + "》"
        holder.itemView.date.text = item.post_date.split(" ")[0]
        holder.itemView.like.text = item.like_count.toString()

        holder.itemView.setOnClickListener({
            var intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("CONTENT", item)
            holder.itemView.context.startActivity(intent)
        })
    }
}