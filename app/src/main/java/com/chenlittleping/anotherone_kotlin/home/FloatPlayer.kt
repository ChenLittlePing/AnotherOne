package com.chenlittleping.anotherone_kotlin.home

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.SeekBar
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.player.IPlayer
import com.chenlittleping.anotherone_kotlin.player.Player
import com.chenlittleping.anotherone_kotlin.player.event.DurationEvent
import kotlinx.android.synthetic.main.float_player.view.*


/**
 * 播放器悬浮窗
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-06 13:14
 *
 */
class FloatPlayer(context: Context): Dialog(context, R.style.FullDialog) {
    private var rootView: View? = null
    private var player: IPlayer = Player

    private var isDraging = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initPlayerBtn()
        initSeekBar()
    }

    private fun initView() {
        rootView = LayoutInflater.from(context).inflate(R.layout.float_player, null)
        setContentView(rootView)

        window.setGravity(Gravity.TOP) //设置Dialog从窗体顶部弹出

        val lp = window.attributes
        var dm = context.resources.displayMetrics
        lp.width = dm.widthPixels //设置宽度为屏幕宽度

        window.attributes = lp
        window.setDimAmount(0.1f) //去掉遮罩
    }

    private fun initPlayerBtn() {
        rootView?.player_last?.setOnClickListener({switchPlayer()})
        rootView?.player_pause_play?.setOnClickListener({ playOrPause()})
    }

    private fun initSeekBar() {
        rootView?.seek_bar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                isDraging = true
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                player.seek(p0?.progress!!)
                isDraging = false
            }

        })
    }

    private fun switchPlayer() {
        player.replay()
    }

    private fun playOrPause() {
        if (player.isPlaying()) {
            player.pause()
        } else {
            player.play()
        }
    }

    override fun dismiss() {
        if (isShowing) {
            super.dismiss()
        }
    }

    override fun show() {
        if (!isShowing) {
            super.show()
            updatePlayPause()
        }
    }

    fun updatePlayInfo(event: DurationEvent) {
        if (event != null) {
            if (isShowing) {
                rootView?.player_title?.text = event.song?.name!!
                rootView?.player_author?.text = event.song?.author
                rootView?.player_from?.text = event.song?.fromWhere

                rootView?.seek_bar?.progress = event.progress
                rootView?.seek_bar?.max = event.duration

                if (!isDraging) {
                    rootView?.player_duration?.text = (event.duration / 60000).toString() + "'" +
                            event.duration / 1000 % (60) + "''"
                }

                updatePlayPause()
            }
        }
    }

    private fun updatePlayPause() {
        if (player.isPlaying()) {
            rootView?.player_pause_play?.setImageResource(R.mipmap.ic_player_pause)
        } else {
            rootView?.player_pause_play?.setImageResource(R.mipmap.ic_player_play)
        }
    }
}