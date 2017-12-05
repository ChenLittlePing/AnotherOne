package com.chenlittleping.anotherone_kotlin.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.home.one.OneFragment
import com.chenlittleping.anotherone_kotlin.net.api.home.HomeRequest
import com.chenlittleping.net.IOut
import kotlinx.android.synthetic.main.fragment_home.view.*


/**
 * 一个分页
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-05 10:03
 *
 */

class HomeFragment : Fragment() {
    private var adapter: Adapter? = null

    private var idList = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater?.inflate(R.layout.fragment_home, container, false)
        initViewPager(rootView)
        getIdList()
        return rootView
    }

    private fun initViewPager(rootView: View?) {
        adapter = Adapter(childFragmentManager)
        rootView?.viewPage?.adapter = adapter
    }

    private fun getIdList() {
        HomeRequest().getIdList(object: IOut<List<String>?> {
            override fun success(r: List<String>?) {
                idList = r as ArrayList<String>
                adapter?.notifyDataSetChanged()
            }

            override fun error(info: String) {
                Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
            }
        })
    }

    inner class Adapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return OneFragment.newInstance(idList[position])
        }

        override fun getCount(): Int {
            return this@HomeFragment.idList?.size
        }
    }
}
