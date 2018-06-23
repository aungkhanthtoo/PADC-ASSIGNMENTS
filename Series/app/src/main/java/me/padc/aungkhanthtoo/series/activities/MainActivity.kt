package me.padc.aungkhanthtoo.series.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log.d
import android.view.Menu
import kotlinx.android.synthetic.main.activity_main.*
import me.padc.aungkhanthtoo.series.fragments.MediatorFragment
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.data.SeriesModel
import me.padc.aungkhanthtoo.series.delegates.CategoryProgramDelegate
import me.padc.aungkhanthtoo.series.delegates.MeMediateDelegate
import me.padc.aungkhanthtoo.series.delegates.CurrentProgramDelegate
import me.padc.aungkhanthtoo.series.events.ApiEvents
import me.padc.aungkhanthtoo.series.fragments.MeFragment
import me.padc.aungkhanthtoo.series.mvp.presenters.MainActivityPresenter
import me.padc.aungkhanthtoo.series.mvp.views.MainActivityView
import me.padc.aungkhanthtoo.series.utils.SERVER_CAN_T_CONNECT
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.design.longSnackbar

class MainActivity : BaseActivity(),
        MainActivityView {

    private lateinit var mPresenter: MainActivityPresenter

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_meditate -> {
                        mPresenter.onTapMediate()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_me -> {
                        mPresenter.onTapMe()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_more -> {
                        mPresenter.onTapMore()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mPresenter = MainActivityPresenter(this)
        mPresenter.onCreate()

        d("lif :MainActivity", "onCreate")
    }

    override fun showMediateScreen() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MediatorFragment.newInstance())
                .commit()
    }

    override fun showMeScreen() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MeFragment())
                .commit()
    }

    override fun showMoreScreen() {
        // Empty for now.
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}
