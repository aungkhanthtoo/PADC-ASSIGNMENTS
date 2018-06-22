package me.padc.aungkhanthtoo.series.data.vo

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Categories", indices = [(Index(value = ["category-id"], unique = true))])
data class CategoryVO(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "category-id")
        @SerializedName("category-id")
        val categoryId: String,

        @SerializedName("title")
        val title: String,

        @Ignore
        @SerializedName("programs")
        val programs: List<ProgramVO>

) : BaseVO()