package com.chenlittleping.anotherone_kotlin.detail

import android.graphics.drawable.AnimationDrawable
import android.view.View
import com.bumptech.glide.Glide
import com.chenlittleping.anotherone_kotlin.R
import com.chenlittleping.anotherone_kotlin.base.BaseActivity
import com.chenlittleping.anotherone_kotlin.common.Constant
import com.chenlittleping.anotherone_kotlin.net.bean.detail.MusicDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.SerialDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.StoryDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.movie.MovieDetail
import com.chenlittleping.anotherone_kotlin.net.bean.detail.question.QuestionDetail
import com.chenlittleping.anotherone_kotlin.net.bean.home.Content
import com.coder.zzq.smartshow.toast.SmartToast
import com.zzhoujay.richtext.RichText
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.detail_answer_info.*
import kotlinx.android.synthetic.main.detail_author_info.*
import kotlinx.android.synthetic.main.detail_movie_header.*
import kotlinx.android.synthetic.main.detail_music_header.*


/**
 * 文章详情页面
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since AnotherOneKotlin
 * @version AnotherOneKotlin
 * @Datetime 2017-12-06 16:59
 *
 */
class DetailActivity : BaseActivity(), DetailContract.View {

    override lateinit var presenter: DetailContract.Presenter

    private var content: Content? = null

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null) {
            presenter.detach()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_detail
    }

    override fun getData() {
        content = intent.getParcelableExtra("CONTENT")
    }

    override fun initUI() {
        RichText.initCacheDir(this)
        when(content?.category) {
            Constant.TYPE_ONE_STORY -> setTitle("阅读")
            Constant.TYPE_ONE_SERIAL -> setTitle("连载")
            Constant.TYPE_ONE_ANSWER -> setTitle("问答")
            Constant.TYPE_ONE_MOVIE -> setTitle(content!!.title)
            Constant.TYPE_ONE_MUSIC -> setTitle("音乐")
        }
        initMvp()
    }

    override fun initMvp() {
        presenter = DetailPresenter(this)

        showLoading()

        when(content?.category) {
            Constant.TYPE_ONE_STORY -> presenter.getEssayDetail(content!!.item_id, content!!.id)
            Constant.TYPE_ONE_SERIAL -> presenter.getSerialDetail(content!!.item_id, content!!.id)
            Constant.TYPE_ONE_ANSWER -> presenter.getAnswerDetail(content!!.item_id, content!!.id)
            Constant.TYPE_ONE_MOVIE -> presenter.getMovieDetail(content!!.item_id)
            Constant.TYPE_ONE_MUSIC -> presenter.getMusicDetail(content!!.item_id)
        }
    }

    override fun updateEssayDetail(detail: StoryDetail?, error: String?) {
        hideLoading()
        if (error != null) {
            SmartToast.show(error)
            return
        }

        story_title.text = detail?.hpTitle
        story_author.text = "文/" + detail?.authors?.get(0) ?.user_name
        story_author.visibility = View.VISIBLE
        story_author_introduce.text = detail?.hpAuthorIntroduce + " " + detail?.editorEmail

        renderHtmlText(detail?.hpContent)

        var it = detail?.authIt?.split("：")
        if (it!!.size > 1) {
            var it1 = it[1]
            author_detail_name.text = detail?.hpAuthor + " " + it1
            Glide.with(this@DetailActivity)
                    .load(detail?.authors?.get(0)?.web_url)
                    .into(author_detail_photo)
            author_detail_layout.visibility = View.VISIBLE
        }

        content_layout.visibility = View.VISIBLE
    }

    override fun updateAnswerDetail(detail: QuestionDetail?, error: String?) {
        hideLoading()
        if (error != null) {
            SmartToast.show(error)
            return
        }
        detail_answer_info.visibility = View.VISIBLE

        story_title.text = detail?.question_title
        asker.text = detail?.asker?.user_name+"问："
        question_content.text = detail?.question_content
        answer.text = detail?.answerer?.user_name+"答："
        story_author_introduce.text = detail?.charge_edt + " " + detail?.charge_email

        renderHtmlText(detail?.answer_content)

        content_layout.visibility = View.VISIBLE
    }

    override fun updateSerialDetail(detail: SerialDetail?, error: String?) {
        hideLoading()
        if (error != null) {
            SmartToast.show(error)
            return
        }

        story_title.text = detail?.title
        story_author.text = "文/" + detail?.author?.user_name
        story_author.visibility = View.VISIBLE
        story_author_introduce.text = detail?.charge_edt + " " + detail?.editor_email

        renderHtmlText(detail?.content)

        content_layout.visibility = View.VISIBLE
    }

    override fun updateMovieDetail(detail: MovieDetail?, error: String?) {
        hideLoading()
        if (error != null) {
            SmartToast.show(error)
            return
        }

        detail_movie_header.visibility = View.VISIBLE
        Glide.with(this@DetailActivity).load(content!!.img_url)
                .into(movie_cover)
        movie_name.text = "《" + content!!.subtitle + "》"

        var movieData = detail?.data?.get(0)
        story_title.text = movieData?.title
        story_author.text = "文/" + movieData?.author_list?.get(0)?.user_name
        story_author.visibility = View.VISIBLE
        story_author_introduce.text = movieData?.charge_edt + " " + movieData?.editor_email

        renderHtmlText(movieData?.content)

        author_detail_name.text = movieData?.author_list?.get(0)?.user_name
        Glide.with(this@DetailActivity)
                .load(movieData?.author_list?.get(0)?.web_url)
                .into(author_detail_photo)

        author_detail_layout.visibility = View.VISIBLE
        content_layout.visibility = View.VISIBLE
    }

    override fun updateMusicDetail(detail: MusicDetail?, error: String?) {
        hideLoading()
        if (error != null) {
            SmartToast.show(error)
            return
        }
        detail_music_header.visibility = View.VISIBLE
        Glide.with(this@DetailActivity).load(detail!!.cover)
                .into(music_cover)
        Glide.with(this@DetailActivity).load(detail!!.feeds_cover)
                .into(music_bg)
        music_name.text =  content!!.subtitle + " · " + content!!.audio_author +
                            " | " + content!!.audio_album

        story_title.text = detail?.story_title
        story_author.text = "文/" + detail?.author_list?.get(0)?.user_name
        story_author.visibility = View.VISIBLE
        story_author_introduce.text = detail?.charge_edt + " " + detail?.editor_email

        renderHtmlText(detail.story)

        author_detail_name.text = detail?.story_author?.user_name + " " + detail?.story_author?.wb_name
        Glide.with(this@DetailActivity)
                .load(detail?.story_author?.web_url)
                .into(author_detail_photo)
        author_detail_layout.visibility = View.VISIBLE

        content_layout.visibility = View.VISIBLE
    }

    private fun renderHtmlText(hpContent: String?) {
        var hp = hpContent
        var headStart = hpContent?.indexOf("<head>")
        var headEnd = hpContent?.indexOf("</head>")
        if (headStart!! >= 0 && headEnd!! >= headStart) {
            hp = hpContent?.removeRange(headStart, headEnd)
        }
        RichText.fromHtml(hp).into(html_text)
    }

    private fun showLoading() {
        var anim = (loading.drawable) as AnimationDrawable
        anim.start()
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        var anim = (loading.drawable) as AnimationDrawable
        anim.stop()
        loading.visibility = View.GONE
    }
}