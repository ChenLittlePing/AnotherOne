package com.chenlittleping.anotherone_kotlin.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chenlittleping.anotherone_kotlin.R


/**
 * 关于
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-11 14:47
 *
 */
class AboutFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater!!.inflate(R.layout.fragment_about, container, false)
}