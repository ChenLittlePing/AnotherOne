package com.chenlittleping.anotherone_kotlin.home

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.home.one.OneFragment
import com.chenlittleping.anotherone_kotlin.net.api.home.HomeRequest
import com.chenlittleping.anotherone_kotlin.net.bean.home.Weather
import com.chenlittleping.anotherone_kotlin.player.event.DurationEvent
import com.chenlittleping.anotherone_kotlin.player.event.PlayingEvent
import com.chenlittleping.net.IOut
import com.coder.zzq.smartshow.toast.SmartToast
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.title_one.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.text.SimpleDateFormat
import java.util.*




/**
 * 一个分页
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-05 10:03
 *
 */

class HomeFragment : Fragment(), OneFragment.IUpdateWeather {

    private var rootView: View? = null

    private var adapter: Adapter? = null

    private var idList = ArrayList<String>()

    private var floatPlayer: FloatPlayer? = null

    private var date = SimpleDateFormat("yyyy-MM-dd")

    private var calendar = Calendar.getInstance()

    private var curPagePost = 0

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_home, container, false)
        initViewPager(rootView)
        initFloatPlayer(rootView)
        getIdList()
        return rootView
    }

    private fun initViewPager(rootView: View?) {
        adapter = Adapter(childFragmentManager)
        rootView?.viewPage?.adapter = adapter
        rootView?.viewPage?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                curPagePost = position
                if (position == 0) {
                    rootView?.date?.text = date.format(Date())
                    rootView?.weather?.visibility = View.VISIBLE
                } else {
                    rootView?.weather?.visibility = View.INVISIBLE
                    calendar.time = Date()//把当前时间赋给日历
                    calendar.add(Calendar.DAY_OF_MONTH, -position)  //设置为前一天
                    var before = calendar.time
                    rootView?.date?.text = date.format(before)
                }
            }

        })
    }

    private fun initFloatPlayer(rootView: View?) {
        rootView?.ivFloatPlayer?.setOnClickListener({ handleFloatPlayerStatus()})

        EventBus.getDefault().register(this)
    }

    private fun handleFloatPlayerStatus() {
        if (floatPlayer != null && floatPlayer!!.isShowing) {
            dismissFloatPlayer()
        } else {
            showFloatPlayer()
        }
    }

    private fun showFloatPlayer() {
        if (floatPlayer == null) {
            floatPlayer = FloatPlayer(context)
        }
        floatPlayer!!.show()
    }

    private fun dismissFloatPlayer() {
        if (floatPlayer != null && floatPlayer!!.isShowing) {
            floatPlayer!!.dismiss()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDurationEvent(durationEvent: DurationEvent) {
        rootView?.ivFloatPlayer?.visibility = View.VISIBLE
        if (floatPlayer != null && floatPlayer!!.isShowing) {
            floatPlayer!!.updatePlayInfo(durationEvent)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPlayerStatusEvent(playingEvent: PlayingEvent) {
        var drawable = rootView?.ivFloatPlayer?.drawable as AnimationDrawable
        if (playingEvent.isPlaying) {
            drawable.start()
        } else {
            drawable.stop()
        }
    }

    private fun getIdList() {
        HomeRequest().getIdList(object: IOut<List<String>?> {
            override fun success(r: List<String>?) {
                idList = r as ArrayList<String>
                adapter?.notifyDataSetChanged()
            }

            override fun error(info: String) {
                SmartToast.show(info)
            }
        })
    }

    inner class Adapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return OneFragment.newInstance(idList[position], this@HomeFragment)
        }

        override fun getCount(): Int {
            return this@HomeFragment.idList.size
        }
    }

    override fun onWeatherUpdate(weather: Weather?) {
        if (curPagePost == 0) {
            rootView?.date?.text = weather?.date
            rootView?.weather?.text = weather?.cityName + "  " +
                    weather?.climate + "  " +
                    weather?.temperature + "℃"
        }
    }
}
