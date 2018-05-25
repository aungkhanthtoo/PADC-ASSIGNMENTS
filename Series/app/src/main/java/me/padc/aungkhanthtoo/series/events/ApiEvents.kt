package me.padc.aungkhanthtoo.series.events

import me.padc.aungkhanthtoo.series.data.vo.BaseVO

class ApiEvents {

    data class ErrorInvokingAPI(val message: String, val status: Int)

    data class SuccessfulDataLoaded(val pageIndex: Int, val newData: List<BaseVO>)

}