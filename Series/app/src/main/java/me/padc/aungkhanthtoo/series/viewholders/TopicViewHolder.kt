package me.padc.aungkhanthtoo.series.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.footer_item_view.view.*

class TopicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val backGroundView: ImageView = itemView.footerItemView
    val iconView: ImageView = itemView.footerItemIcon
    val firstText: TextView = itemView.footerText1
    val secondText: TextView = itemView.footerText2
}