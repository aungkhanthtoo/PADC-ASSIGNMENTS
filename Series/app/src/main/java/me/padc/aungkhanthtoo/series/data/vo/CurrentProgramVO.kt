package me.padc.aungkhanthtoo.series.data.vo

import com.google.gson.annotations.SerializedName

data class CurrentProgramVO (

    @SerializedName("program-id")
    val programId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("current-period")
    val currentPeriod: String,

    @SerializedName("background")
    val background: String,

    @SerializedName("average-lengths")
    val averageLengths: List<Int>,

    @SerializedName("description")
    val description: String,

    @SerializedName("sessions")
    val sessions: List<SessionVO>

): BaseVO()



