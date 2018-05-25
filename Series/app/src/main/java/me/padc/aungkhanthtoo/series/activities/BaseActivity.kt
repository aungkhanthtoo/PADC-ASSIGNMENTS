package me.padc.aungkhanthtoo.series.activities

import android.support.v7.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus

abstract class BaseActivity : AppCompatActivity(){

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}