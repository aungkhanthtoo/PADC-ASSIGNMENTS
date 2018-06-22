package me.padc.aungkhanthtoo.series.data.vo

import android.arch.persistence.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Sessions",
        foreignKeys = [(ForeignKey(entity = CurrentProgramVO::class,
                childColumns = ["program-id"],
                parentColumns = ["program-id"],
                onDelete = ForeignKey.CASCADE)),
            (ForeignKey(entity = ProgramVO::class,
                    childColumns = ["program-id"],
                    parentColumns = ["program-id"],
                    onDelete = ForeignKey.CASCADE))],
        indices = [(Index(value = ["session-id"], unique = true))])
data class SessionVO(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "program-id")
        val currentProgramId: String,

        @ColumnInfo(name = "session-id")
        @SerializedName("session-id")
        val sessionId: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("length-in-seconds")
        val lengthInSeconds: Int,

        @SerializedName("file-path")
        val filePath: String

) : BaseVO()
