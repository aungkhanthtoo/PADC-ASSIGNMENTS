package me.padc.aungkhanthtoo.series

import android.app.Application
import me.padc.aungkhanthtoo.series.data.SeriesModel

class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
        SeriesModel.loadSeriesData()
    }

}