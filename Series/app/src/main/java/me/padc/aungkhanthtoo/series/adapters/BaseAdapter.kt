package me.padc.aungkhanthtoo.series.adapters

import android.support.v7.widget.RecyclerView
import me.padc.aungkhanthtoo.series.data.vo.BaseVO
import me.padc.aungkhanthtoo.series.viewholders.BaseViewHolder

abstract class BaseAdapter<T : BaseViewHolder<out BaseVO>, W> : RecyclerView.Adapter<T>() {

    protected var mData: MutableList<W> = ArrayList()

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: T, position: Int) {
        holder as BaseViewHolder<W>
        holder.setData(mData[position])
    }

    fun setNewItems(data: List<W>) {
        if (mData.isNotEmpty()) mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun appendNewItems(data: List<W>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int) =
            if (position in 0 until mData.size) {
                mData[position]
            } else {
                null
            }

    fun getItems() = mData

    fun addNewItem(data: W) {
        mData.add(data)
        notifyItemInserted(mData.lastIndex)
    }

    fun removeItem(data: W) {
        val position = mData.indexOf(data)
        mData.remove(data)
        notifyItemRemoved(position)
    }

    fun clearData() {
        if (mData.isNotEmpty()) {
            mData.clear()
            notifyDataSetChanged()
        }
    }

}