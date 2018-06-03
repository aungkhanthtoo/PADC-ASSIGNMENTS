package me.padc.aungkhanthtoo.series.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_item_view.view.*
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.data.vo.CategoryVO
import me.padc.aungkhanthtoo.series.data.vo.ProgramVO
import me.padc.aungkhanthtoo.series.delegates.CategoryProgramDelegate
import me.padc.aungkhanthtoo.series.utils.data.getCategoryPics

class CategoryItemViewHolder(itemView: View, private val mDelegate: CategoryProgramDelegate) : BaseViewHolder<ProgramVO>(itemView) {

    private val image: ImageView = itemView.programItemImage
    private val label: TextView = itemView.programItemLabel
    private val length: TextView = itemView.programItemLength
    private var programVO: ProgramVO? = null
    lateinit var rootCategory: CategoryVO

    override fun setData(data: ProgramVO) {
        Glide.with(context).load(getCategoryPics()[adapterPosition % getCategoryPics().size]).into(image)
        label.text = data.title
        length.text = context.resources.getString(R.string.minutes, data.averageLengths[0])
        programVO = data
    }

    override fun onClick(v: View) {
        mDelegate.onTapCategoryProgramItem(programVO!!.programId, rootCategory.categoryId)
    }

}