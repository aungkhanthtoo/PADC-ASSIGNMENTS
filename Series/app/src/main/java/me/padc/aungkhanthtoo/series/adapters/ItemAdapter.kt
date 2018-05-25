package me.padc.aungkhanthtoo.series.adapters

import android.view.ViewGroup
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.data.vo.ProgramVO
import me.padc.aungkhanthtoo.series.utils.inflate
import me.padc.aungkhanthtoo.series.viewholders.CategoryItemViewHolder

class ItemAdapter : BaseAdapter<CategoryItemViewHolder, ProgramVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CategoryItemViewHolder(parent.inflate(R.layout.category_item_view))

     override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        holder.setDataWithPosition(mData[position], position)
    }

}