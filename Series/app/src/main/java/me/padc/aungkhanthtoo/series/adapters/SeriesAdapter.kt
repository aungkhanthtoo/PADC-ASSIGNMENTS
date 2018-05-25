package me.padc.aungkhanthtoo.series.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.data.vo.*
import me.padc.aungkhanthtoo.series.utils.inflate
import me.padc.aungkhanthtoo.series.viewholders.CategoryViewHolder
import me.padc.aungkhanthtoo.series.viewholders.CurrentProgramViewHolder
import me.padc.aungkhanthtoo.series.viewholders.TitleViewHolder
import me.padc.aungkhanthtoo.series.viewholders.TopicViewHolder

class SeriesAdapter : BaseAdapter<RecyclerView.ViewHolder, BaseVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> CurrentProgramViewHolder(parent.inflate(R.layout.current_program_item_view))
            MIDDLE -> CategoryViewHolder(parent.inflate(R.layout.category_view))
            TITLE -> TitleViewHolder(parent.inflate(R.layout.title_item_view))
            FOOTER -> TopicViewHolder(parent.inflate(R.layout.topic_item_view))
            else -> throw IllegalArgumentException("Invalid View Type!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mData[position]
        when (item) {
            is CurrentProgramVO -> {
                holder as CurrentProgramViewHolder
                holder.setData(item)
            }
            is CategoryVO -> {
                holder as CategoryViewHolder
                holder.setData(item)
            }
            is TopicVO -> {
                holder as TopicViewHolder
                holder.setDataWithPosition(item, position)
            }
            is TitleVO -> {
                holder as TitleViewHolder
                holder.setData(item)
            }
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