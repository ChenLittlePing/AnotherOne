package com.chenlittleping.anotherone_kotlin.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.net.bean.home.HomeData
import kotlinx.android.synthetic.main.fragment_home.view.*


/**
 * 首页
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 15:14
 *
 */
class HomeFragment : Fragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter

    private var  adapter = HomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMvp()
    }

    override fun initMvp() {
        presenter = HomePresenter(this)
        presenter.attach()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_home, container, false)
        initList(rootView)
        presenter.getIdList()
        return rootView
    }

    private fun initList(rootView : View?) {
        rootView?.list?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rootView?.list?.adapter = adapter
    }

    override fun updateIdList(list: List<String>?, error: String?) {
        if (error != null) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            return
        }
        if (list != null && list.isNotEmpty()) {
            presenter.getOneList(list[0])
        }
    }

    override fun updateOneList(data : HomeData?, error: String?) {
        if (error != null) {
            return
        }
        adapter.bind(data?.content_list)
    }
}