package me.padc.aungkhanthtoo.series.viewholders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide.init

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

    val context: Context = itemView.context

    init {
        itemView.setOnClickListener(this)
    }

    abstract fun setData(data: T)
}