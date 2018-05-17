package me.padc.aungkhanthtoo.series.viewholders

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.footer_item_view.view.*
import kotlinx.android.synthetic.main.middle_item_view.view.*
import kotlinx.android.synthetic.main.series_item_view.view.*
import kotlinx.android.synthetic.main.start_here_sub_item_view.view.*
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.adapters.SeriesAdapter
import me.padc.aungkhanthtoo.series.utils.data.FooterItem
import me.padc.aungkhanthtoo.series.utils.data.HeaderItem
import me.padc.aungkhanthtoo.series.utils.data.Item
import me.padc.aungkhanthtoo.series.utils.data.MidItem
import me.padc.aungkhanthtoo.series.utils.inflate

class SeriesViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
    val recycler: RecyclerView = itemView.listItemRecycler
    val title: TextView = itemView.listItemTitle

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = itemView.imageViewHeader
    }

    class MiddleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = itemView.middleItemImage
        val label: TextView = itemView.middleItemLabel
    }

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val backGroundView: ImageView = itemView.footerItemView
        val iconView: ImageView = itemView.footerItemIcon
        val firstText: TextView = itemView.footerText1
        val secondText: TextView = itemView.footerText2
    }

    class ItemAdapter(val data: Item) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        lateinit var mContext: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            mContext = parent.context
            return when (viewType) {
                SeriesAdapter.HEADER -> HeaderViewHolder(parent.inflate(R.layout.start_here_sub_item_view))
                SeriesAdapter.MIDDLE -> MiddleViewHolder(parent.inflate(R.layout.middle_item_view))
                SeriesAdapter.FOOTER -> FooterViewHolder(parent.inflate(R.layout.footer_item_view))
                else -> throw IllegalArgumentException(" Invalid View Type!")
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (data is MidItem) {
                val middleViewHolder = holder as MiddleViewHolder
                // Picasso.with(mContext).load(data.list[position].first).into(middleViewHolder.image)
                Glide.with(mContext).load(data.list[position].first).into(middleViewHolder.image)
                middleViewHolder.label.text = data.list[position].second
            } else if (data is FooterItem) {
                val footerViewHolder = holder as FooterViewHolder
                footerViewHolder.iconView.setImageResource(data.list[position].icon)
                Glide.with(mContext).load(data.list[position].background).into(footerViewHolder.backGroundView)
                //footerViewHolder.backGroundView.setBackgroundResource(data.list[position].background)
                footerViewHolder.firstText.text = data.list[position].first
                footerViewHolder.secondText.text = data.list[position].second
            }
        }

        override fun getItemCount() = data.size

        override fun getItemViewType(position: Int) = when (data) {
            is HeaderItem -> SeriesAdapter.HEADER
            is MidItem -> SeriesAdapter.MIDDLE
            is FooterItem -> SeriesAdapter.FOOTER
        }
    }

}