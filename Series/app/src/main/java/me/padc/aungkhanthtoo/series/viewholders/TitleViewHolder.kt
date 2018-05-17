package me.padc.aungkhanthtoo.series.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.title_item_view.view.*

class TitleViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val title : TextView = itemView.listItemTitle
}