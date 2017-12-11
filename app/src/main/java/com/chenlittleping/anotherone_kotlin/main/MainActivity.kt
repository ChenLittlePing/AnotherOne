package com.chenlittleping.anotherone_kotlin.main

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MenuItem
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.R.id.navigation_one
import com.chenlittleping.anotherone_kotlin.R.id.navigation_read
import com.chenlittleping.anotherone_kotlin.base.BaseActivity
import com.chenlittleping.anotherone_kotlin.home.OneFragment
import com.chenlittleping.anotherone_kotlin.home.ReadingListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var menuId = arrayListOf(R.id.navigation_one, R.id.navigation_read, R.id.navigation_me)

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initUI() {
        initViewPager()
        initBottom()
    }

    private fun initViewPager() {
        var fragments = ArrayList<Fragment>()
        fragments.add(OneFragment())
        fragments.add(ReadingListFragment())
        viewPage.adapter = MainPageAdapter(supportFragmentManager, fragments)
        viewPage.setOnClickListener(
                {view -> run {
                    Log.d("", "")
                    view.id
                }}
        )
        viewPage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                navigation.selectedItemId = menuId[position]
            }

        })
    }

    private fun initBottom() {
        navigation.setOnNavigationItemSelectedListener { item: MenuItem -> onNaviSelected(item)}
    }

    private fun onNaviSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            navigation_one -> viewPage.currentItem = 0
            navigation_read -> viewPage.currentItem = 1
        }
        return true
    }
}
