package me.padc.aungkhanthtoo.series.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_item_view.view.*
import me.padc.aungkhanthtoo.series.data.vo.ProgramVO
import me.padc.aungkhanthtoo.series.utils.data.getCategoryPics

class CategoryItemViewHolder(itemView: View) : BaseViewHolder<ProgramVO>(itemView) {

    private val image: ImageView = itemView.programItemImage
    private val label: TextView = itemView.programItemLabel
    private val length: TextView = itemView.programItemLength

    override fun setData(data: ProgramVO) {

    }

    fun setDataWithPosition(data: ProgramVO, position: Int) {
        Glide.with(itemView.context).load(getCategoryPics()[position % getCategoryPics().size]).into(image)
        label.text = data.title
        length.text = "${data.averageLengths[0].toString()} mins"
    }

    override fun onClick(v: View) {

    }

}