package me.padc.aungkhanthtoo.series.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import me.padc.aungkhanthtoo.series.data.SeriesModel
import me.padc.aungkhanthtoo.series.utils.data.getCategoryPics
import me.padc.aungkhanthtoo.series.utils.data.getCurrentPic

class ProgramDetailActivity : AppCompatActivity() {


    private val mAdapter by lazy {
        SessionAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_program_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ViewCompat.setTransitionName(image, SHARED_IMAGE_NAME)

        val dataType = intent.getIntExtra(EXTRA_TYPE, -1)
        if (dataType == CURRENT_PROGRAM_TYPE) {
            val currentProgram = SeriesModel.getCurrentProgram()
            if (currentProgram != null) {
                supportActionBar?.title = currentProgram.title
                programDescription.text = currentProgram.description
                Glide.with(this).load(getCurrentPic()).into(image)

                with(sessionRecycler) {
                    itemAnimator = DefaultItemAnimator()
                    adapter = mAdapter
                }
                mAdapter.setNewItems(currentProgram.sessions)
            }

        } else if (dataType == CATEGORY_PROGRAM_TYPE) {
            val categoryId = intent.getStringExtra(EXTRA_CATEGORY_ID)
            val programId = intent.getStringExtra(EXTRA_PROGRAM_ID)
            val categoryProgram = SeriesModel.getCategoryProgram(categoryId, programId)
            supportActionBar?.title = categoryProgram?.title
            programDescription.text = categoryProgram?.description
            Glide.with(this).load(getCategoryPics()[0]).into(image)

            with(sessionRecycler) {
                itemAnimator = DefaultItemAnimator()
                adapter = mAdapter
            }
            mAdapter.setNewItems(categoryProgram?.sessions ?: ArrayList())
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

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
       private const val CURRENT_PROGRAM_TYPE = 0
       private const val CATEGORY_PROGRAM_TYPE = 1

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
