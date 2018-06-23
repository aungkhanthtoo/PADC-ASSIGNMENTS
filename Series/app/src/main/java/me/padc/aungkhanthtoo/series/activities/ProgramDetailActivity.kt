package me.padc.aungkhanthtoo.series.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.view.Menu
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_program_detail.*
import kotlinx.android.synthetic.main.content_program_detail.*
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.adapters.SessionAdapter
import me.padc.aungkhanthtoo.series.data.vo.CurrentProgramVO
import me.padc.aungkhanthtoo.series.data.vo.ProgramVO
import me.padc.aungkhanthtoo.series.data.vo.SessionVO
import me.padc.aungkhanthtoo.series.mvp.presenters.SeriesDetailPresenter
import me.padc.aungkhanthtoo.series.mvp.views.SeriesDetailView
import me.padc.aungkhanthtoo.series.utils.data.getCategoryPics
import me.padc.aungkhanthtoo.series.utils.data.getCurrentPic

class ProgramDetailActivity : AppCompatActivity(), SeriesDetailView {

    private lateinit var mPresenter: SeriesDetailPresenter

    private val mAdapter by lazy { SessionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_program_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ViewCompat.setTransitionName(image, SHARED_IMAGE_NAME)

        val dataType = intent.getIntExtra(EXTRA_TYPE, -1)
        check(dataType != -1) { "Start ProgramDetailActivity using only static factory methods." }

        mPresenter = SeriesDetailPresenter(this)
        mPresenter.onLoadDetailsData(dataType,
                intent.getStringExtra(EXTRA_CATEGORY_ID),
                intent.getStringExtra(EXTRA_PROGRAM_ID))

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }

    override fun showCurrentProgram(program: CurrentProgramVO) {
        updateUI(program.title, program.description, getCurrentPic(), program.sessions)
    }

    override fun showCategoryProgram(program: ProgramVO) {
        updateUI(program.title, program.description, getCategoryPics()[0], program.sessions)
    }

    private fun updateUI(title: String, description: String, @DrawableRes resId: Int, sessions: List<SessionVO>) {
        supportActionBar?.title = title
        programDescription.text = description
        Glide.with(this).load(resId).into(image)

        with(sessionRecycler) {
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }
        mAdapter.setNewItems(sessions)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_program_detail, menu)
        return true
    }

    companion object {
        private const val EXTRA_PROGRAM_ID = "programId"
        private const val EXTRA_CATEGORY_ID = "categoryId"
        private const val EXTRA_TYPE = "dataType"
        private const val SHARED_IMAGE_NAME = "sharedImageView"
        const val CURRENT_PROGRAM_TYPE = 0
        const val CATEGORY_PROGRAM_TYPE = 1

        fun newIntentCurrentProgram(context: Context): Intent {
            return Intent(context, ProgramDetailActivity::class.java).apply {
                putExtra(EXTRA_TYPE, CURRENT_PROGRAM_TYPE)
            }
        }

        fun newIntentCategoryProgram(context: Context, programId: String, categoryId: String): Intent {
            return Intent(context, ProgramDetailActivity::class.java).apply {
                putExtra(EXTRA_TYPE, CATEGORY_PROGRAM_TYPE)
                putExtra(EXTRA_PROGRAM_ID, programId)
                putExtra(EXTRA_CATEGORY_ID, categoryId)
            }
        }
    }
}
