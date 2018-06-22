package me.padc.aungkhanthtoo.series.data.vo

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Topics", indices = [Index("topic-name")])
data class TopicVO(

        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "topic-name")
        @SerializedName("topic-name")
        val topicName: String,

        @SerializedName("topic-desc")
        val topicDesc: String,

        @SerializedName("icon")
        val icon: String,

        @SerializedName("background")
        val background: String

) : BaseVO()