package me.padc.aungkhanthtoo.series.data.vo

import com.google.gson.annotations.SerializedName

data class CategoryVO (

    @SerializedName("category-id")
    val categoryId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("programs")
    val programs: List<ProgramVO>

): BaseVO()