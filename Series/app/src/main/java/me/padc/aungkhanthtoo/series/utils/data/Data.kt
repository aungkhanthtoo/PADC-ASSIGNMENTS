package me.padc.aungkhanthtoo.series.utils.data

import me.padc.aungkhanthtoo.series.R
import java.util.*

fun getCategoryPics() = listOf(R.drawable.stones, R.drawable.flower, R.drawable.sit, R.drawable.boat)

fun getCurrentPic() = R.drawable.start

fun getTopicPics() = listOf(
        R.drawable.ic_stars_black_24dp to R.drawable.basic,
        R.drawable.ic_relax to R.drawable.relaxtwo,
        R.drawable.ic_sleep to R.drawable.sleep,
        R.drawable.ic_focus to R.drawable.focus,
        R.drawable.ic_well_being to R.drawable.well,
        R.drawable.ic_resilience to R.drawable.trouble,
        R.drawable.ic_health to R.drawable.healthy_lifestyle,
        R.drawable.ic_relationship to R.drawable.relationship,
        R.drawable.ic_unguided to R.drawable.unguided
)

val randomObj = Random()

fun getRandomTopicPics(): Pair<Int, Int> {
    val random = randomObj.nextInt(getTopicPics().size)
    return getTopicPics()[random]
}

fun getRandomCategoryPics(): Int {
    val random = randomObj.nextInt(getCategoryPics().size)
    return getCategoryPics()[random]
}
