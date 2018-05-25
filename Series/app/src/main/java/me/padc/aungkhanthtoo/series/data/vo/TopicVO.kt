package me.padc.aungkhanthtoo.series.data.vo

import com.google.gson.annotations.SerializedName

data class TopicVO (

    @SerializedName("topic-name")
    val topicName: String,

    @SerializedName("topic-desc")
    val topicDesc: String,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("background")
    val background: String

): BaseVO()