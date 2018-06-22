package me.padc.aungkhanthtoo.series.adapters

import android.view.ViewGroup
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.data.vo.*
import me.padc.aungkhanthtoo.series.delegates.CategoryProgramDelegate
import me.padc.aungkhanthtoo.series.delegates.CurrentProgramDelegate
import me.padc.aungkhanthtoo.series.mvp.presenters.SeriesListPresenter
import me.padc.aungkhanthtoo.series.utils.inflate
import me.padc.aungkhanthtoo.series.viewholders.*

class SeriesAdapter(private val mDelegate: SeriesListPresenter) : BaseAdapter<BaseViewHolder<out BaseVO>, BaseVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<out BaseVO> {
        return when (viewType) {
            HEADER -> CurrentProgramViewHolder(parent.inflate(R.layout.current_program_item_view), mDelegate)
            MIDDLE -> CategoryViewHolder(parent.inflate(R.layout.category_view), mDelegate)
            TITLE -> TitleViewHolder(parent.inflate(R.layout.title_item_view))
            FOOTER -> TopicViewHolder(parent.inflate(R.layout.topic_item_view))
            else -> throw IllegalArgumentException("Invalid View Type!")
        }
    }

    override fun getItemViewType(position: Int) = when (mData[position]) {
        is CurrentProgramVO -> HEADER
        is CategoryVO -> MIDDLE
        is TopicVO -> FOOTER
        is TitleVO -> TITLE
        else -> throw IllegalArgumentException(" Invalid VO Type!")
    }

    companion object {
        const val HEADER = 0x0
        const val MIDDLE = 120
        const val FOOTER = 230
        const val TITLE = 999
    }
}