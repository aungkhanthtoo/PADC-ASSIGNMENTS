package me.padc.aungkhanthtoo.series.data.vo

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName
import me.padc.aungkhanthtoo.series.data.persistence.converters.AverageLengthConverter

@Entity(tableName = "Programs",
        indices = [(Index(value = ["program-id"], unique = true))])
@TypeConverters(AverageLengthConverter::class)

data class ProgramVO(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "program-id")
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

        @Ignore
        @SerializedName("sessions")
        val sessions: List<SessionVO>

) : BaseVO()