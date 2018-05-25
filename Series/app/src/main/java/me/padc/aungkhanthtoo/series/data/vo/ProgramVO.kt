package me.padc.aungkhanthtoo.series.data.vo

import com.google.gson.annotations.SerializedName


data class ProgramVO (

    @SerializedName("program-id")
    val programId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("average-lengths")
    val averageLengths: List<Int>,

    @SerializedName("description")
    val description: String,

    @SerializedName("sessions")
    val sessions: List<SessionVO>

): BaseVO()