package me.padc.aungkhanthtoo.series.network.responses

import me.padc.aungkhanthtoo.series.data.vo.CurrentProgramVO

data class GetCurrentProgramResponse(
        val currentProgram: CurrentProgramVO
) : BaseResponse()