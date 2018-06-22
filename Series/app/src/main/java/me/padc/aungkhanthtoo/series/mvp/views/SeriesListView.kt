package me.padc.aungkhanthtoo.series.mvp.views

import me.padc.aungkhanthtoo.series.data.vo.BaseVO

interface SeriesListView : BaseView {

    fun showSeriesList(data : List<BaseVO>)

    fun showErrorMsg(msg: String)

    fun showCategoryProgramDetail(programId: String, categoryId: String)

    fun showCurrentProgramDetail()

}