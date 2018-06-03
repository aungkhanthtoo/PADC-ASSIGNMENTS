package me.padc.aungkhanthtoo.series.viewholders

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.category_view.view.*
import me.padc.aungkhanthtoo.series.adapters.ItemAdapter
import me.padc.aungkhanthtoo.series.data.vo.CategoryVO
import me.padc.aungkhanthtoo.series.delegates.CategoryProgramDelegate

class CategoryViewHolder(itemView: View, private val mDelegate: CategoryProgramDelegate) : BaseViewHolder<CategoryVO>(itemView) {

    private val title: TextView = this.itemView.categoryTitle
    private val recycler: RecyclerView = itemView.listItemRecycler
    private val mAdapter by lazy { ItemAdapter(mDelegate) }

    override fun setData(data: CategoryVO) {
        title.text = data.title
        with(recycler) {
            adapter = mAdapter
            itemAnimator = DefaultItemAnimator()
        }
        mAdapter.setNewItems(data.programs)
        mAdapter.rootCategory = data
    }

    override fun onClick(v: View) {

    }

}