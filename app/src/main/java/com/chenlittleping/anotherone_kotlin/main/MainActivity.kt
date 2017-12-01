package com.chenlittleping.anotherone_kotlin.main

import android.support.v4.app.Fragment
import android.util.Log
import com.chenlittleping.anotherone_kotlin.base.BaseActivity
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initUI() {
        initViewPager()
    }

    private fun initViewPager() {
        var fragments = ArrayList<Fragment>()
        fragments.add(HomeFragment())
        viewPage.adapter = MainPageAdapter(supportFragmentManager, fragments)
        viewPage.setOnClickListener(
                {view -> run {
                    Log.d("", "")
                    view.id
                }}
        )
    }
}
