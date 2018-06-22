package me.padc.aungkhanthtoo.series.mvp.presenters

import me.padc.aungkhanthtoo.series.mvp.views.BaseView

abstract class BasePresenter<V : BaseView>(val view: V) {

    open fun onCreate() {}
    open fun onStart() {}
    open fun onResume() {}
    open fun onPause() {}
    open fun onStop() {}
    open fun onDestroy() {}

}