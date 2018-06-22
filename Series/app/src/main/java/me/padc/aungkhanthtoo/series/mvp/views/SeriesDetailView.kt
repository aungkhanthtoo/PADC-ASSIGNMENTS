package me.padc.aungkhanthtoo.series.mvp.views

import me.padc.aungkhanthtoo.series.data.vo.CurrentProgramVO
import me.padc.aungkhanthtoo.series.data.vo.ProgramVO

interface SeriesDetailView : BaseView{

    fun showCurrentProgram(program: CurrentProgramVO)

    fun showCategoryProgram(program: ProgramVO)

}