package me.padc.aungkhanthtoo.series.viewholders

import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.title_item_view.view.*
import me.padc.aungkhanthtoo.series.data.vo.TitleVO

class TitleViewHolder(view: View) : BaseViewHolder<TitleVO>(view){

    private val title : TextView = itemView.listItemTitle

    override fun setData(data: TitleVO) {
        title.text = data.title
    }

    override fun onClick(v: View) {

    }

}