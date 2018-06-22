package me.padc.aungkhanthtoo.series.data.persistence.converters

import android.arch.persistence.room.TypeConverter

class AverageLengthConverter {

    companion object {

        @TypeConverter
        fun toString(lengths: List<Int>): String {
            val builder = StringBuilder()
            lengths.forEach {
                builder.append(it.toString()).append(",")
            }
            builder.deleteCharAt(builder.lastIndex)
            return builder.toString()
        }

        @TypeConverter
        fun toIntList(length: String): List<Int> {
            val intList = ArrayList<Int>()
            val lengthString = length.split(',')
            lengthString.forEach { intList.add(it.toInt()) }
            return intList
        }

    }
}