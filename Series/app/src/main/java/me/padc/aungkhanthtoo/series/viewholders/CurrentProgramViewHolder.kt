package me.padc.aungkhanthtoo.series.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.current_program_item_view.view.*
import me.padc.aungkhanthtoo.series.data.vo.CurrentProgramVO
import me.padc.aungkhanthtoo.series.utils.data.getCurrentPic

class CurrentProgramViewHolder(itemView: View) : BaseViewHolder<CurrentProgramVO>(itemView) {

   private val image: ImageView = itemView.currentProgramImage
   private val title: TextView = itemView.currentProgramTitle
   private val length: TextView = itemView.currentProgramLength
   private val startText: TextView = itemView.startButton

    override fun setData(data: CurrentProgramVO) {
        Glide.with(itemView.context).load(getCurrentPic()).into(image)
        title.text = data.title
        length.text = "${data.averageLengths[0].toString()} mins"
        startText.text = data.currentPeriod
    }

    override fun onClick(v: View) {

    }
}