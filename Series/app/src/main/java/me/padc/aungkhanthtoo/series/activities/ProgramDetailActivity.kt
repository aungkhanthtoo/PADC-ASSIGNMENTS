package me.padc.aungkhanthtoo.series.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
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
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val categoryPosition = intent.getIntExtra(CATEGORY_POSITION, -1)

        if (categoryPosition == -1) {
            val currentProgram = SeriesModel.currentProgram
            supportActionBar?.title = currentProgram.title
            programDescription.text = currentProgram.description
            Glide.with(this).load(getCurrentPic()).into(image)

            with(sessionRecycler) {
                itemAnimator = DefaultItemAnimator()
                adapter = mAdapter
            }
            mAdapter.setNewItems(currentProgram.sessions)
        } else {
            val position = intent.getIntExtra(POSITION, -1)
            val categoryProgram = SeriesModel.getCategoryProgram(position, categoryPosition)
            supportActionBar?.title = categoryProgram.title
            programDescription.text = categoryProgram.description
            Glide.with(this).load(getCategoryPics()[position]).into(image)

            with(sessionRecycler) {
                itemAnimator = DefaultItemAnimator()
                adapter = mAdapter
            }
            mAdapter.setNewItems(categoryProgram.sessions)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_program_detail, menu)
        return true
    }

    companion object {
        const val POSITION = "position"
        const val CATEGORY_POSITION = "categoryPosition"
    }
}
