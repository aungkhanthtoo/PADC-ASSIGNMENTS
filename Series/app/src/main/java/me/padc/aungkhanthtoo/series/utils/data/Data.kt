package me.padc.aungkhanthtoo.series.utils.data

import me.padc.aungkhanthtoo.series.R

sealed class Item{
    abstract val size: Int
}

data class HeaderItem(val title: String, val list : List<Pair<Int, String>>, override val size: Int = list.size) : Item()
data class MidItem(val title: String, val list : List<Pair<Int, String>>, override val size: Int = list.size) : Item()
data class FooterItem(val title: String, val list : List<FooterPojo>, override val size: Int = list.size) : Item()

data class FooterPojo(val icon: Int, val background: Int, val first: String, val second: String)

fun getHeaderData() = listOf(R.drawable.start to "Start Here")

fun getHealthyData() = listOf(R.drawable.stones to "Heal From Trauma", R.drawable.flower to "Overthinking", R.drawable.sit to "Dealing With Bullies", R.drawable.boat to "Worry Free Day")

fun getMorningData() = listOf(R.drawable.sit to "Improve Focus", R.drawable.flower to "Worry Free Day",  R.drawable.stones to "Morning Meditation", R.drawable.boat to "Overthinking")

fun getNewData() = listOf(R.drawable.flower to "Loneliness", R.drawable.stones to "Be More Self-Aware",  R.drawable.sit to "Public Speaking", R.drawable.boat to "Drift to sleep")

fun getSleepData() = listOf(R.drawable.deep_sleep to "Deep Sleep", R.drawable.flower to "Peaceful Evening",  R.drawable.stones to "Drift to sleep", R.drawable.boat to "Be More Self-Aware")

fun getPopularData() = listOf(R.drawable.start to "Simple Habit Starter", R.drawable.flower to "Staying Calm",  R.drawable.stones to "Release Blame",R.drawable.boat to "Public Speaking")

fun getAllTopicData() = listOf(FooterPojo(R.drawable.ic_stars_black_24dp, R.drawable.basic, "Basic", "Learn meditation fundamentals"),
        FooterPojo(R.drawable.ic_relax, R.drawable.relaxtwo, "Relax", "Unwind and relieve stress"),
        FooterPojo(R.drawable.ic_sleep, R.drawable.sleep, "Sleep", "Rest effortlessly in deep sleep"),
        FooterPojo(R.drawable.ic_focus, R.drawable.focus, "Focus", "Clear the mind for high performance"),
        FooterPojo(R.drawable.ic_well_being, R.drawable.well, "Well-being", "Inspire joy, abundance, and purpose"),
        FooterPojo(R.drawable.ic_resilience, R.drawable.trouble, "Resilience", "Face challenges with strength"),
        FooterPojo(R.drawable.ic_health, R.drawable.healthy_lifestyle, "Health", "Care for your mind and body"),
        FooterPojo(R.drawable.ic_relationship, R.drawable.relationship, "Relationships", "Maintain lasting connections"),
        FooterPojo(R.drawable.ic_unguided, R.drawable.unguided, "Unguided", "Silence and nature sounds"))

fun getData() = listOf(
        HeaderItem("Start Here", getHeaderData()),
        MidItem("Morning Meditations", getMorningData()),
        MidItem("A Healthy Mind", getHealthyData()),
        MidItem("New on Simple Habit", getNewData()),
        MidItem("To Sleep Better", getSleepData()),
        MidItem("Most Popular", getPopularData()),
        FooterItem("All Topics", getAllTopicData())
)