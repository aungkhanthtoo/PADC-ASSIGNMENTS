package me.padc.aungkhanthtoo.series.viewholders

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.category_view.view.*
import me.padc.aungkhanthtoo.series.adapters.ItemAdapter
import me.padc.aungkhanthtoo.series.data.vo.CategoryVO
import me.padc.aungkhanthtoo.series.delegates.CategoryProgramDelegate
import me.padc.aungkhanthtoo.series.delegates.CurrentProgramDelegate

class CategoryViewHolder(itemView: View, private val mDelegate: CategoryProgramDelegate) : BaseViewHolder<CategoryVO>(itemView) {

    private val recycler: RecyclerView = itemView.listItemRecycler

    override fun setData(data: CategoryVO) {
        itemView.categoryTitle.text = data.title
        val itemAdapter = ItemAdapter(mDelegate, adapterPosition)
        with(recycler) {
            adapter = itemAdapter
            itemAnimator = DefaultItemAnimator()
        }
        itemAdapter.setNewItems(data.programs)
    }

    override fun onClick(v: View) {

    }

}