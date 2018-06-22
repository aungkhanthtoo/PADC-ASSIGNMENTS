package me.padc.aungkhanthtoo.series.mvp.presenters

import me.padc.aungkhanthtoo.series.data.SeriesModel
import me.padc.aungkhanthtoo.series.delegates.CategoryProgramDelegate
import me.padc.aungkhanthtoo.series.delegates.CurrentProgramDelegate
import me.padc.aungkhanthtoo.series.events.ApiEvents
import me.padc.aungkhanthtoo.series.mvp.views.SeriesListView
import me.padc.aungkhanthtoo.series.utils.SERVER_CAN_T_CONNECT
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class SeriesListPresenter(view: SeriesListView) : BasePresenter<SeriesListView>(view),
        CurrentProgramDelegate, CategoryProgramDelegate {

    override fun onCreate() {
        super.onCreate()
        SeriesModel.loadSeriesData()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun onDataLoaded(event: ApiEvents.SuccessfulDataLoaded) {
        view.showSeriesList(event.newData)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onError(event: ApiEvents.ErrorInvokingAPI) {
        EventBus.getDefault().removeStickyEvent(ApiEvents.ErrorInvokingAPI::class.java)
        if (event.status == SERVER_CAN_T_CONNECT) {
            view.showErrorMsg(event.message)
        }
    }

    override fun onTapCurrentProgram() {
        view.showCurrentProgramDetail()
    }

    override fun onTapCategoryProgramItem(programId: String, categoryId: String) {
        view.showCategoryProgramDetail(programId, categoryId)
    }

    fun onLoadPersistenceData(emptyAdapter: Boolean) {
        if (emptyAdapter && SeriesModel.mDataSource.isNotEmpty()) {
            view.showSeriesList(SeriesModel.mDataSource)
        }
    }

}