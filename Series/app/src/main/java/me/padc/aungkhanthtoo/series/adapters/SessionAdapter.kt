package me.padc.aungkhanthtoo.series.adapters

import android.view.ViewGroup
import me.padc.aungkhanthtoo.series.R
import me.padc.aungkhanthtoo.series.data.vo.SessionVO
import me.padc.aungkhanthtoo.series.utils.inflate
import me.padc.aungkhanthtoo.series.viewholders.SessionViewHolder

class SessionAdapter: BaseAdapter<SessionViewHolder, SessionVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            SessionViewHolder(parent.inflate(R.layout.session_item_view))

}