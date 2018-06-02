package me.padc.aungkhanthtoo.series.data

import android.util.Log.d
import me.padc.aungkhanthtoo.series.data.SeriesModel.mDataSource
import me.padc.aungkhanthtoo.series.data.vo.BaseVO
import me.padc.aungkhanthtoo.series.data.vo.CategoryVO
import me.padc.aungkhanthtoo.series.data.vo.CurrentProgramVO
import me.padc.aungkhanthtoo.series.data.vo.ProgramVO
import me.padc.aungkhanthtoo.series.events.ApiEvents
import me.padc.aungkhanthtoo.series.network.SeriesDataAgentImpl
import me.padc.aungkhanthtoo.series.utils.ACCESS_TOKEN
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

object SeriesModel {

    private var mPageIndex = 1
    val mDataSource: MutableList<BaseVO> by lazy {
        ArrayList<BaseVO>()
    }

    init {
        EventBus.getDefault().register(this)
    }

    fun loadSeriesData() {
        SeriesDataAgentImpl.loadSeriesData(ACCESS_TOKEN, mPageIndex)
    }

    val currentProgram: CurrentProgramVO
        get() = mDataSource[1] as CurrentProgramVO

    fun getCategoryProgram(position: Int, categoryPosition: Int): ProgramVO{
        val category = mDataSource[categoryPosition] as CategoryVO
        return category.programs[position]
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onDataLoaded(event: ApiEvents.SuccessfulDataLoaded) {
        d("SeriesModel", "DataLoadedEvent is received.")
        mDataSource.addAll(event.newData)
        mPageIndex = event.pageIndex + 1
    }

}