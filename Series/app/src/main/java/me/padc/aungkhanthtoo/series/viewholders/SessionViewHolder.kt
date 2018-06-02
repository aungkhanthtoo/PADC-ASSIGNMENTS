package me.padc.aungkhanthtoo.series.viewholders

import android.graphics.Color
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.session_item_view.view.*
import me.padc.aungkhanthtoo.series.data.vo.SessionVO

class SessionViewHolder(itemView: View): BaseViewHolder<SessionVO>(itemView) {
    private val number: TextView = itemView.sessionNum
    private val title: TextView = itemView.sessionTitle
    private val length: TextView = itemView.sessionLength

    override fun setData(data: SessionVO) {
        
        with(number){
            text = (adapterPosition+1).toString()
            if(adapterPosition == 0) setTextColor(Color.WHITE)
        }
        with(title){
            text = data.title
            if(adapterPosition == 0) setTextColor(Color.WHITE)
        }
        with(length){
            text = getMinutes(data.lengthInSeconds)
            if(adapterPosition == 0) setTextColor(Color.WHITE)
        }

    }

    override fun onClick(v: View?) {

    }

    private fun getMinutes(lengthInSeconds: Int):String{
        return "${lengthInSeconds / 60}:${lengthInSeconds % 60}"
    }
}