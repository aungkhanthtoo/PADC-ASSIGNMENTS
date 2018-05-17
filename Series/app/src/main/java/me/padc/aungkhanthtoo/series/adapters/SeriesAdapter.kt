package me.padc.aungkhanthtoo.series.adapters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.bumptech.glide.Glide
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.utils.data.*
import me.padc.aungkhanthtoo.series.utils.inflate
import me.padc.aungkhanthtoo.series.viewholders.SeriesViewHolder
import me.padc.aungkhanthtoo.series.viewholders.TitleViewHolder
import me.padc.aungkhanthtoo.series.viewholders.TopicViewHolder

class SeriesAdapter(private val data: List<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context

        return when(viewType){
            HEADER, MIDDLE -> SeriesViewHolder(parent.inflate(R.layout.series_item_view))
            TITLE -> TitleViewHolder(parent.inflate(R.layout.title_item_view))
            FOOTER -> TopicViewHolder(parent.inflate(R.layout.footer_item_view))
            else -> throw IllegalArgumentException("Invalid View Type!")
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        when (item) {
            is HeaderItem -> {
                holder as SeriesViewHolder
                holder.title.text = item.title
                holder.recycler.layoutManager = LinearLayoutManager(holder.itemView.context)
                holder.recycler.adapter = SeriesViewHolder.ItemAdapter(item)
            }
            is MidItem -> {
                holder as SeriesViewHolder
                holder.title.text = item.title
                holder.recycler.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
                holder.recycler.adapter = SeriesViewHolder.ItemAdapter(item)
            }
            is FooterItem -> {
                holder as TopicViewHolder
                holder.iconView.setImageResource(item.icon)
                Glide.with(mContext).load(item.background).into(holder.backGroundView)
                holder.firstText.text = item.first
                holder.secondText.text = item.second
            }
            is TitleItem -> {
                holder as TitleViewHolder
                holder.title.text = item.title
            }
        }
    }

    override fun getItemViewType(position: Int) = when (data[position]) {
        is HeaderItem -> HEADER
        is MidItem -> MIDDLE
        is FooterItem -> FOOTER
        is TitleItem -> TITLE
    }

    companion object {
        const val HEADER = 0x0
        const val MIDDLE = 120
        const val FOOTER = 230
        const val TITLE = 1000
    }
}