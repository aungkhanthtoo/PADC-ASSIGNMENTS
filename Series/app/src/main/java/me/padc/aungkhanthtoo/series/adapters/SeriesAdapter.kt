package me.padc.aungkhanthtoo.series.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.utils.data.FooterItem
import me.padc.aungkhanthtoo.series.utils.data.HeaderItem
import me.padc.aungkhanthtoo.series.utils.data.Item
import me.padc.aungkhanthtoo.series.utils.data.MidItem
import me.padc.aungkhanthtoo.series.utils.inflate
import me.padc.aungkhanthtoo.series.viewholders.SeriesViewHolder

class SeriesAdapter(private val data: List<Item>) : RecyclerView.Adapter<SeriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val itemView = parent.inflate(R.layout.series_item_view)
        return SeriesViewHolder(itemView)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val item = data[position]
        when (item) {
            is HeaderItem -> {
                holder.title.text = item.title
                holder.recycler.layoutManager = LinearLayoutManager(holder.itemView.context)
            }
            is MidItem -> {
                holder.title.text = item.title
                holder.recycler.layoutManager = LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }
            is FooterItem -> {
                holder.title.text = item.title
                holder.recycler.layoutManager = LinearLayoutManager(holder.itemView.context)
            }
        }
        holder.recycler.adapter = SeriesViewHolder.ItemAdapter(item)
    }

    override fun getItemViewType(position: Int) = when (data[position]) {
        is HeaderItem -> HEADER
        is MidItem -> MIDDLE
        is FooterItem -> FOOTER
    }

    companion object {
        const val HEADER = 0x0
        const val MIDDLE = 120
        const val FOOTER = 230
    }
}