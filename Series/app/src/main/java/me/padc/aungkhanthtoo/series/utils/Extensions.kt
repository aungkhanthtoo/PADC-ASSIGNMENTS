package me.padc.aungkhanthtoo.series.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int) =  LayoutInflater.from(this.context).inflate(layoutId, this, false)

