package me.padc.aungkhanthtoo.series.activities

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log.d
import android.util.Log.e
import android.view.Menu
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import me.padc.aungkhanthtoo.series.fragments.MediatorFragment
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.data.SeriesModel
import me.padc.aungkhanthtoo.series.events.ApiEvents
import me.padc.aungkhanthtoo.series.fragments.MeFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.design.longSnackbar

class MainActivity : BaseActivity(),
        MediatorFragment.OnFragmentInteractionListener {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MediatorFragment.newInstance())
                .commitNow()

        val bool1 = EventBus.getDefault().hasSubscriberForEvent(ApiEvents.ErrorInvokingAPI::class.java)
        val bool2 = EventBus.getDefault().hasSubscriberForEvent(ApiEvents.SuccessfulDataLoaded::class.java)
        d("MainActivity", "$bool1 : $bool2")
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onError(event: ApiEvents.ErrorInvokingAPI){
        e("MainActivity", "Error event ${event.message}")
        EventBus.getDefault().removeStickyEvent(ApiEvents.ErrorInvokingAPI::class.java)
        longSnackbar(findViewById<View>(R.id.container)!!, "Can't connect to Server", "Try Again"){
            SeriesModel.loadSeriesData()
        }
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
