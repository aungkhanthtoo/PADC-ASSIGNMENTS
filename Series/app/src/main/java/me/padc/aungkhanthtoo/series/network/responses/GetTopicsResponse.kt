package me.padc.aungkhanthtoo.series.network.responses

import me.padc.aungkhanthtoo.series.data.vo.TopicVO

data class GetTopicsResponse (
        val topics: List<TopicVO>
) : BaseResponse()