package me.padc.aungkhanthtoo.series.data.vo

import com.google.gson.annotations.SerializedName


data class SessionVO (

    @SerializedName("session-id")
    val sessionId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("length-in-seconds")
    val lengthInSeconds: Int,

    @SerializedName("file-path")
    val filePath: String

): BaseVO()
