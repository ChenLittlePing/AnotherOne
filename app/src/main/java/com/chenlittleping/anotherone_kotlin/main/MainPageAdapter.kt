package com.chenlittleping.anotherone_kotlin.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


/**
 * 主页适配器
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-11-22 14:45
 *
 */
class MainPageAdapter : FragmentPagerAdapter {

    private var mFragments : ArrayList<Fragment>

    constructor(fm : FragmentManager, fgs : ArrayList<Fragment>) : super(fm) {
        mFragments = fgs
    }

    override fun getItem(position: Int): Fragment {
        return mFragments?.get(position)
    }

    override fun getCount(): Int {
        return mFragments?.size
    }
}