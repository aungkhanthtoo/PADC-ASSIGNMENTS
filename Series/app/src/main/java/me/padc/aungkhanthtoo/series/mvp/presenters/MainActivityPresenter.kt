package me.padc.aungkhanthtoo.series.mvp.presenters

import me.padc.aungkhanthtoo.series.mvp.views.MainActivityView

class MainActivityPresenter(view: MainActivityView) : BasePresenter<MainActivityView>(view) {

    override fun onCreate() {
        super.onCreate()
        view.showMediateScreen()
    }

    fun onTapMediate(){
        view.showMediateScreen()
    }

    fun onTapMe(){
        view.showMeScreen()
    }

    fun onTapMore(){
        view.showMoreScreen()
    }

}