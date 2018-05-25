package me.padc.aungkhanthtoo.series.components

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import me.padc.aungkhanthtoo.series.R

class ProgressView : ConstraintLayout {

    lateinit var dayStreak: TextView
    lateinit var session: TextView
    lateinit var minute: TextView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun init(context: Context) {
        val rootView = View.inflate(context, R.layout.progress_compound_view, this)
        dayStreak = rootView.findViewById(R.id.dayStreakText)
        session = rootView.findViewById(R.id.sessionText)
        minute = rootView.findViewById(R.id.minutesText)
    }

    fun setDaystreak(day: Int){
        dayStreak.text = day.toString()
    }

    fun setSession(session: Int){
        this.session.text = session.toString()
    }

    fun setMinutes(minutes: Int){
        minute.text = minutes.toString()
    }

}