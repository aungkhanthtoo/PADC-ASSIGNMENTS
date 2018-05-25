package me.padc.aungkhanthtoo.series.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.topic_item_view.view.*
import me.padc.aungkhanthtoo.series.data.vo.TopicVO
import me.padc.aungkhanthtoo.series.utils.data.getTopicPics

class TopicViewHolder(itemView: View) : BaseViewHolder<TopicVO>(itemView) {

    private val backGroundView: ImageView = itemView.topicItemBackground
    private val iconView: ImageView = itemView.icon
    private val firstText: TextView = itemView.label
    private val secondText: TextView = itemView.subLabel

    override fun setData(data: TopicVO) {

    }

    fun setDataWithPosition(data: TopicVO, position: Int) {
        Glide.with(itemView.context).load(getTopicPics()[position % getTopicPics().size].second).into(backGroundView)
        iconView.setImageResource(getTopicPics()[position % getTopicPics().size].first)
        firstText.text = data.topicName
        secondText.text = data.topicDesc
    }

    override fun onClick(v: View) {

    }

}