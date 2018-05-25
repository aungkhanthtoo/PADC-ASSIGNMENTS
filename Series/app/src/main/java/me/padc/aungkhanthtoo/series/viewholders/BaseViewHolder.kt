package me.padc.aungkhanthtoo.series.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<W>(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }
    
    abstract fun setData(data: W)
}