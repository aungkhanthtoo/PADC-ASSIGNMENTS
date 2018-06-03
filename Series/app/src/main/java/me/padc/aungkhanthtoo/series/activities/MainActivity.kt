package me.padc.aungkhanthtoo.series.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
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
import me.padc.aungkhanthtoo.series.utils.SERVER_CAN_T_CONNECT
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.intentFor

class MainActivity : BaseActivity(),
        MeMediateDelegate, CurrentProgramDelegate, CategoryProgramDelegate {

    override fun onTapCategoryProgramItem(programId: String, categoryId: String) {
        startActivity(ProgramDetailActivity.newIntentCategoryProgram(applicationContext, programId, categoryId))
    }

    override fun onTapCurrentProgram() {
        startActivity(ProgramDetailActivity.newIntentCurrentProgram(applicationContext))
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, MediatorFragment.newInstance())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, MeFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun setScreenTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MediatorFragment.newInstance())
                .commitNow()

        val bool1 = EventBus.getDefault().hasSubscriberForEvent(ApiEvents.ErrorInvokingAPI::class.java)
        val bool2 = EventBus.getDefault().hasSubscriberForEvent(ApiEvents.SuccessfulDataLoaded::class.java)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onError(event: ApiEvents.ErrorInvokingAPI) {
        EventBus.getDefault().removeStickyEvent(ApiEvents.ErrorInvokingAPI::class.java)
        if (event.status == SERVER_CAN_T_CONNECT) {
            longSnackbar(findViewById(R.id.container)!!, "Can't connect to Server", "Try Again") {
                SeriesModel.loadSeriesData()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}
