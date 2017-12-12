package com.chenlittleping.anotherone_kotlin.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.home.one.OneAdapter
import com.chenlittleping.anotherone_kotlin.net.api.home.HomeRequest
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.chenlittleping.net.IOut
import com.coder.zzq.smartshow.toast.SmartToast
import kotlinx.android.synthetic.main.fragment_reading_list.view.*
import kotlinx.android.synthetic.main.title_activity.view.*


/**
 * 阅读
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-11 09:28
 *
 */
class ReadingListFragment: Fragment() {

    private lateinit var rootView: View
    
    private var adapter = OneAdapter()
    private var contents: List<Content>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater?.inflate(R.layout.fragment_reading_list, container, false)!!
        initList()
        initTitle()
        initSwipe()
        if (contents != null && contents!!.isNotEmpty()) {
            adapter.bind(contents)
        } else {
            load()
        }
        return rootView!!
    }

    private fun initTitle() {
        rootView.title_text.text = "阅读"
        rootView.back.visibility = View.GONE
    }

    private fun initList() {
        rootView.reading_list.layoutManager = LinearLayoutManager(activity)
        rootView.reading_list.adapter = adapter
    }
    
    private fun initSwipe() {
        rootView.swipe.setOnRefreshListener { load() }
        rootView.swipe.isRefreshing = true
    }

    private fun load() {
        rootView.swipe.isRefreshing = true
        HomeRequest().getReadingList(object : IOut<List<Content>?> {
            override fun success(r: List<Content>?) {
                rootView.swipe.isRefreshing = false
                contents = r
                adapter.bind(r)
            }

            override fun error(info: String) {
                rootView.swipe.isRefreshing = false
                SmartToast.show(info)
            }
        })
    }

}