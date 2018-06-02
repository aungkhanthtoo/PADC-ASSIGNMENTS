package me.padc.aungkhanthtoo.series.adapters

import android.view.ViewGroup
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.data.vo.ProgramVO
import me.padc.aungkhanthtoo.series.delegates.CategoryProgramDelegate
import me.padc.aungkhanthtoo.series.delegates.CurrentProgramDelegate
import me.padc.aungkhanthtoo.series.utils.inflate
import me.padc.aungkhanthtoo.series.viewholders.CategoryItemViewHolder

class ItemAdapter(private val delegate: CategoryProgramDelegate, private val categoryPosition: Int) : BaseAdapter<CategoryItemViewHolder, ProgramVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CategoryItemViewHolder(parent.inflate(R.layout.category_item_view), delegate, categoryPosition)

}