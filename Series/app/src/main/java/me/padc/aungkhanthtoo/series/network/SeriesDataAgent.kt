package me.padc.aungkhanthtoo.series.network

interface SeriesDataAgent {

    fun loadSeriesData(accessToken: String, pageNo: Int)
}