package me.padc.aungkhanthtoo.series.mvp.presenters

import me.padc.aungkhanthtoo.series.activities.ProgramDetailActivity
import me.padc.aungkhanthtoo.series.data.SeriesModel
import me.padc.aungkhanthtoo.series.mvp.views.SeriesDetailView

class SeriesDetailPresenter(view: SeriesDetailView) : BasePresenter<SeriesDetailView>(view) {

    fun onLoadDetailsData(type: Int, categoryId: String?, programId: String?) =
            when (type) {
                ProgramDetailActivity.CURRENT_PROGRAM_TYPE -> {
                    view.showCurrentProgram(SeriesModel.getCurrentProgram()!!)
                }
                ProgramDetailActivity.CATEGORY_PROGRAM_TYPE -> {
                    view.showCategoryProgram(SeriesModel.getCategoryProgram(categoryId!!, programId!!) ?:
                    throw IllegalStateException("Invalid CategoryId or ProgramId!"))
                }
                else -> {
                    throw IllegalArgumentException("Invalid Program Type!")
                }
            }

}