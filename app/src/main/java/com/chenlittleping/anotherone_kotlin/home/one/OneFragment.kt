package com.chenlittleping.anotherone_kotlin.home.one

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.net.bean.home.HomeData
import com.chenlittleping.anotherone_kotlin.net.bean.home.Weather
import kotlinx.android.synthetic.main.fragment_one.view.*


/**
 * 首页
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 15:14
 *
 */
class OneFragment : Fragment(), OneContract.View {

    override lateinit var presenter: OneContract.Presenter

    private var rootView: View? = null

    private var  adapter = OneAdapter()

    private var listId: String? = null

    companion object {
        var iUpdateWeather: IUpdateWeather? = null

        fun newInstance(id: String, l: IUpdateWeather): Fragment {
            iUpdateWeather = l
            var bundle = Bundle()
            bundle.putString("ID", id)
            var fragment = OneFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listId = arguments["ID"] as String
        initMvp()
    }

    override fun initMvp() {
        presenter = OnePresenter(this)
        presenter.attach()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_one, container, false)
        initList(rootView)
        initSwipe(rootView)
        presenter.getOneList(listId!!)
        return rootView
    }

    private fun initList(rootView : View?) {
        rootView?.list?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rootView?.list?.adapter = adapter
    }

    private fun initSwipe(rootView: View?) {
        rootView?.swipe?.setOnRefreshListener { presenter.getOneList(listId!!) }
    }

    override fun updateOneList(data : HomeData?, error: String?) {
        if (error != null) {
            return
        }
        rootView?.swipe?.isRefreshing = false
        adapter.bind(data?.content_list)

        iUpdateWeather?.onWeatherUpdate(data?.weather)
    }

    interface IUpdateWeather {
        fun onWeatherUpdate(weather: Weather?)
    }
}